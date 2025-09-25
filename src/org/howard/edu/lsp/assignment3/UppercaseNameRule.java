package org.howard.edu.lsp.assignment3;

/** Rule (1): uppercase Name. */
public class UppercaseNameRule implements TransformerRule {
    @Override
    public void apply(Product p) {
        if (p.getName() != null) {
            p.setName(p.getName().toUpperCase());
        }
    }
}

