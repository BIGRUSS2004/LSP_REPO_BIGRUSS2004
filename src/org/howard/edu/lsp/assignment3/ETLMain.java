package org.howard.edu.lsp.assignment3;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

/**
 * A3 entry point that wires Extractor -> Transformer -> Loader.
 * Behavior matches Assignment 2 exactly (paths, transforms, error handling, summary printout).
 *
 * Run from repo root:
 *   javac -d bin src/org/howard/edu/lsp/assignment3/*.java
 *   java -cp bin org.howard.edu.lsp.assignment3.ETLMain
 */
public class ETLMain {
    private static final String INPUT_PATH  = "data/products.csv";
    private static final String OUTPUT_PATH = "data/transformed_products.csv";

    public static void main(String[] args) {
        System.out.println("ETL Pipeline (A3 OO) starting...");
        Extractor extractor = new CSVExtractor();
        Loader loader = new CSVLoader();

        List<TransformerRule> rules = Arrays.asList(
            new UppercaseNameRule(),
            new ElectronicsDiscountRule(),
            new PremiumElectronicsUpgradeRule(),
            new PriceRangeRule()
        );
        ProductTransformer transformer = new ProductTransformer(rules);

        try {
            List<String[]> input = extractor.extract(INPUT_PATH);
            TransformResult result = transformer.transform(input);
            loader.load(OUTPUT_PATH, result.rows);
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
}

