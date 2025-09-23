package org.howard.edu.lsp.assignment3;

import java.util.ArrayList;
import java.util.List;

/**
 * Applies a sequence (pipeline) of {@link TransformRule}s to each {@link Product}.
 * <p>To match Assignment 2 exactly, re-implement the same transformations here as rules.
 * The default configuration only trims whitespace so it is unlikely to alter outputs.</p>
 */
public class Transformer {
    private final List<TransformRule> rules = new ArrayList<>();

    public Transformer addRule(TransformRule rule) {
        rules.add(rule);
        return this;
    }

    /** Transforms a record by applying all rules in order. */
    public Product transform(Product p) {
        for (TransformRule rule : rules) {
            p = rule.apply(p);
        }
        return p;
    }
}
