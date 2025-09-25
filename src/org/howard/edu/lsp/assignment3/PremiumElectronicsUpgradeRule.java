package org.howard.edu.lsp.assignment3;

import java.math.BigDecimal;

/** Rule (3): if final price > 500 AND original category was "Electronics", set Category="Premium Electronics". */
public class PremiumElectronicsUpgradeRule implements TransformerRule {
    private static final BigDecimal THRESH = new BigDecimal("500.00");

    @Override
    public void apply(Product p) {
        if ("Electronics".equalsIgnoreCase(p.getOriginalCategory())
                && p.getPrice().compareTo(THRESH) > 0) {
            p.setCategory("Premium Electronics");
        }
    }
}

