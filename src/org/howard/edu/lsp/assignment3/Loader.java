package org.howard.edu.lsp.assignment3;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Writes {@link Product} rows to a CSV file at the provided output path.
 * <p>Relative path must match Assignment 2 behavior (e.g., "data/transformed_products.csv").</p>
 */
public class Loader {
    private final String outputPath;
    private String[] headers;

    public Loader(String outputPath) {
        this.outputPath = outputPath;
    }

    /** Sets the CSV header order before writing rows. */
    public Loader withHeaders(String[] headers) {
        this.headers = headers;
        return this;
    }

    /**
     * Writes the rows to the output CSV. If headers are not set, they will be inferred
     * from the first product's field order.
     */
    public void load(List<Product> rows) throws IOException {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(outputPath))) {
            if ((headers == null || headers.length == 0) && !rows.isEmpty()) {
                headers = rows.get(0).headers().iterator().next().getClass().isAssignableFrom(String.class)
                        ? rows.get(0).headers().toString().split(",") : null;
            }
            if (headers == null && !rows.isEmpty()) {
                // Fallback: infer headers by iterating the first row
                var first = rows.get(0);
                var headerList = new java.util.ArrayList<String>();
                for (var h : first.headers()) headerList.add(h);
                headers = headerList.toArray(new String[0]);
            }

            if (headers != null) {
                bw.write(String.join(",", headers));
                bw.newLine();
            }

            for (Product p : rows) {
                var values = new java.util.ArrayList<String>();
                for (String h : headers) {
                    String v = p.get(h);
                    values.add(v == null ? "" : v);
                }
                bw.write(values.stream().collect(Collectors.joining(",")));
                bw.newLine();
            }
        }
    }
}
