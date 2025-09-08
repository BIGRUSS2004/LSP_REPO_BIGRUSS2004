package org.howard.edu.lsp.assignment2;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

/**
 * Assignment 2: CSV ETL with relative paths.
 * Reads data/products.csv -> transforms -> writes data/transformed_products.csv
 * Output columns: ProductID,Name,Price,Category,PriceRange
 *
 * Transform order:
 *  (1) uppercase Name
 *  (2) 10% discount if Category is "Electronics" (then round HALF_UP to 2 decimals)
 *  (3) if final price > 500 AND original category was "Electronics", set Category="Premium Electronics"
 *  (4) PriceRange from final price: Low/Medium/High/Premium
 *
 * Handles:
 *  - Missing input file: print error and exit
 *  - Empty input (only header): write header-only output
 *
 * Run (from repo root):
 *   javac -d bin src/org/howard/edu/lsp/assignment2/ETLPipeline.java
 *   java -cp bin org.howard.edu.lsp.assignment2.ETLPipeline
 */
public class ETLPipeline {
    private static final String INPUT_PATH  = "data/products.csv";
    private static final String OUTPUT_PATH = "data/transformed_products.csv";

    private static final class TransformResult {
        final List<String[]> rows; // includes header
        final int read, transformed, skipped;
        TransformResult(List<String[]> rows, int read, int transformed, int skipped) {
            this.rows = rows; this.read = read; this.transformed = transformed; this.skipped = skipped;
        }
    }

    public static void main(String[] args) {
        System.out.println("ETL Pipeline starting...");
        try {
            List<String[]> input = extract(INPUT_PATH);
            TransformResult result = transform(input);
            load(OUTPUT_PATH, result.rows);
            System.out.printf(
                "Run summary:%n  rows read: %d%n  transformed: %d%n  skipped: %d%n  output: %s%n",
                result.read, result.transformed, result.skipped, OUTPUT_PATH
            );
        } catch (FileNotFoundException e) {
            System.err.println("Error: Missing input file at " + INPUT_PATH);
        } catch (IOException e) {
            System.err.println("I/O Error: " + e.getMessage());
        }
    }

    /** Extract CSV: returns lines split by comma (keeps empty fields). */
    static List<String[]> extract(String path) throws IOException {
        List<String[]> rows = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            String line;
            while ((line = br.readLine()) != null) {
                rows.add(line.split(",", -1));
            }
        }
        return rows;
    }

    /** Transform per spec; always returns a header row first. */
    static TransformResult transform(List<String[]> rows) {
        List<String[]> out = new ArrayList<>();
        out.add(new String[]{"ProductID","Name","Price","Category","PriceRange"});

        if (rows == null || rows.size() == 0) {
            return new TransformResult(out, 0, 0, 0);
        }

        int startIdx = looksLikeHeader(rows.get(0)) ? 1 : 0;
        int read = Math.max(rows.size() - startIdx, 0);
        int transformed = 0, skipped = 0;

        for (int i = startIdx; i < rows.size(); i++) {
            String[] r = rows.get(i);
            if (r == null || r.length < 4) { skipped++; continue; }

            String idRaw   = r[0].trim();
            String nameRaw = r[1].trim();
            String priceRaw= r[2].trim();
            String catRaw  = r[3].trim();

            if (idRaw.isEmpty() || nameRaw.isEmpty() || priceRaw.isEmpty()) { skipped++; continue; }

            BigDecimal price;
            try { price = new BigDecimal(priceRaw); }
            catch (NumberFormatException nfe) { skipped++; continue; }

            String originalCategory = catRaw;
            String name = nameRaw.toUpperCase();

            if ("Electronics".equalsIgnoreCase(originalCategory)) {
                price = price.multiply(new BigDecimal("0.90"));
            }
            price = price.setScale(2, RoundingMode.HALF_UP);

            String category = originalCategory;
            if (price.compareTo(new BigDecimal("500.00")) > 0 &&
                "Electronics".equalsIgnoreCase(originalCategory)) {
                category = "Premium Electronics";
            }

            String range;
            int cmp10  = price.compareTo(new BigDecimal("10.00"));
            int cmp100 = price.compareTo(new BigDecimal("100.00"));
            int cmp500 = price.compareTo(new BigDecimal("500.00"));
            if (cmp10 <= 0)        range = "Low";
            else if (cmp100 <= 0)  range = "Medium";
            else if (cmp500 <= 0)  range = "High";
            else                   range = "Premium";

            out.add(new String[]{ idRaw, name, price.toPlainString(), category, range });
            transformed++;
        }
        return new TransformResult(out, read, transformed, skipped);
    }

    /** Load CSV; ensures parent folder exists; writes header+rows given. */
    static void load(String path, List<String[]> rows) throws IOException {
        File outFile = new File(path);
        File parent = outFile.getParentFile();
        if (parent != null) parent.mkdirs();
        try (PrintWriter pw = new PrintWriter(new FileWriter(outFile, false))) {
            for (String[] row : rows) pw.println(String.join(",", row));
        }
    }

    private static boolean looksLikeHeader(String[] row0) {
        if (row0 == null || row0.length < 4) return false;
        return row0[0].trim().equalsIgnoreCase("ProductID")
            && row0[1].trim().equalsIgnoreCase("Name")
            && row0[2].trim().equalsIgnoreCase("Price")
            && row0[3].trim().equalsIgnoreCase("Category");
    }
}
