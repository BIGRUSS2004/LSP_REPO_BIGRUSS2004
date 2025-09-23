package org.howard.edu.lsp.assignment3;

/**
 * A single transformation step that can be applied to a {@link Product}.
 * Implementations should mutate the product in-place (or no-op) and return it.
 */
public interface TransformRule {
    Product apply(Product product);
}
