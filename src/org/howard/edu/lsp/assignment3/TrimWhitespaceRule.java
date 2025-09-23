package org.howard.edu.lsp.assignment3;

/**
 * Trims leading/trailing whitespace from all field values.
 * This is a safe default that usually does not change semantics.
 */
public class TrimWhitespaceRule implements TransformRule {
    @Override
    public Product apply(Product product) {
        for (var entry : product.entries()) {
            String key = entry.getKey();
            String val = entry.getValue();
            if (val != null) {
                entry.setValue(val.strip());
            }
        }
        return product;
    }
}
