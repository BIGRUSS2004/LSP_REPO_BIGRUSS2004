package org.howard.edu.lsp.assignment3;

import java.math.BigDecimal;
import java.math.RoundingMode;

/** Rule (2): 10% discount if original Category == "Electronics", then round HALF_UP to 2 decimals. */
public class ElectronicsDiscountRule implements TransformerRule {
    private static final BigDecimal DISCOUNT = new BigDecimal("0.90");

    @Override
    public void apply(Product p) {
        if ("Electronics".equalsIgnoreCase(p.getOriginalCategory())) {
            p.setPrice(p.getPrice().multiply(DISCOUNT));
        }
        // Ensure rounding to 2 decimals after discount step
        p.setPrice(p.getPrice().setScale(2, RoundingMode.HALF_UP));
    }
}

