package org.howard.edu.lsp.assignment3;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Orchestrates Extract -> Transform -> Load using relative paths:
 * <ul>
 *   <li>Input:  data/products.csv</li>
 *   <li>Output: data/transformed_products.csv</li>
 * </ul>
 * 
 * <p>Run from project root (so relative paths resolve):</p>
 * <pre>
 * javac -d bin src/org/howard/edu/lsp/assignment3/*.java
 * java -cp bin org.howard.edu.lsp.assignment3.ETLMain
 * </pre>
 */
public class ETLMain {
    private static final String INPUT  = "data/products.csv";
    private static final String OUTPUT = "data/transformed_products.csv";

    public static void main(String[] args) {
        Extractor extractor = new Extractor(INPUT);
        Transformer transformer = new Transformer()
                // TODO: Recreate the exact A2 transformations as rules to ensure identical output.
                // .addRule(new SomeSpecificRule())
                .addRule(new TrimWhitespaceRule());

        Loader loader = new Loader(OUTPUT);

        try {
            var rows = extractor.extract();
            List<Product> out = new ArrayList<>(rows.size());
            for (Product p : rows) {
                out.add(transformer.transform(p));
            }

            // Infer headers from first row to preserve A2 column order
            if (!out.isEmpty()) {
                var headerList = new java.util.ArrayList<String>();
                for (var h : out.get(0).headers()) headerList.add(h);
                loader.withHeaders(headerList.toArray(new String[0]));
            }
            loader.load(out);
            System.out.println("ETL completed. Wrote: " + OUTPUT);
        } catch (IOException e) {
            System.err.println("ETL failed: " + e.getMessage());
        }
    }
}
