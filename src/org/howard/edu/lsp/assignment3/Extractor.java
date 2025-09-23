package org.howard.edu.lsp.assignment3;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Extracts input rows from a CSV file into a list of {@link Product} objects.
 * <p>Relative path must match Assignment 2 behavior (e.g., "data/products.csv").</p>
 * <p><b>CSV Note:</b> This simple reader expects no embedded commas inside quoted fields.
 * If Assignment 2 handled quoted CSV, port that exact logic here to keep outputs identical.</p>
 */
public class Extractor {
    private final String inputPath;

    public Extractor(String inputPath) {
        this.inputPath = inputPath;
    }

    /**
     * Reads the CSV and returns a list of Product rows.
     * @return list of products (possibly empty if file has only header)
     * @throws IOException if the file cannot be read
     */
    public List<Product> extract() throws IOException {
        List<Product> rows = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(inputPath))) {
            String headerLine = br.readLine();
            if (headerLine == null || headerLine.isBlank()) {
                // Empty or missing header: return empty result
                return rows;
            }
            String[] headers = headerLine.split(",", -1);

            String line;
            while ((line = br.readLine()) != null) {
                if (line.isBlank()) continue;
                String[] values = line.split(",", -1);
                Product p = new Product();
                for (int i = 0; i < headers.length; i++) {
                    String key = headers[i];
                    String val = (i < values.length) ? values[i] : "";
                    p.put(key, val);
                }
                rows.add(p);
            }
        }
        return rows;
    }
}
