package org.howard.edu.lsp.assignment3;

/**
 * A single transformation step that mutates a Product in-place.
 * Used polymorphically by ProductTransformer.
 */
public interface TransformerRule {
    void apply(Product p);
}

