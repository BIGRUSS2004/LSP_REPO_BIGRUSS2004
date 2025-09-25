package org.howard.edu.lsp.assignment3;

import java.math.BigDecimal;

/** Rule (4): set priceRange from final price: <=10 Low, <=100 Medium, <=500 High, else Premium. */
public class PriceRangeRule implements TransformerRule {
    private static final BigDecimal TEN   = new BigDecimal("10.00");
    private static final BigDecimal HUND  = new BigDecimal("100.00");
    private static final BigDecimal FIVEH = new BigDecimal("500.00");

    @Override
    public void apply(Product p) {
        int c10  = p.getPrice().compareTo(TEN);
        int c100 = p.getPrice().compareTo(HUND);
        int c500 = p.getPrice().compareTo(FIVEH);
        String range = (c10 <= 0) ? "Low"
                : (c100 <= 0) ? "Medium"
                : (c500 <= 0) ? "High"
                : "Premium";
        p.setPriceRange(range);
    }
}

