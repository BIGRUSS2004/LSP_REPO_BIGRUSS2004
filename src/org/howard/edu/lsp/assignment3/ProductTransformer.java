package org.howard.edu.lsp.assignment3;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Orchestrates row->Product mapping, applies polymorphic rules, and emits output rows.
 * Preserves A2 behavior: header detection, skipping invalid rows, identical output header/columns.
 */
public class ProductTransformer {
    private final List<TransformerRule> rules;

    public ProductTransformer(List<TransformerRule> rules) {
        this.rules = rules;
    }

    public TransformResult transform(List<String[]> rows) {
        List<String[]> out = new ArrayList<>();
        out.add(new String[]{"ProductID","Name","Price","Category","PriceRange"});

        if (rows == null || rows.isEmpty()) {
            return new TransformResult(out, 0, 0, 0);
        }

        int startIdx = looksLikeHeader(rows.get(0)) ? 1 : 0;
        int read = Math.max(rows.size() - startIdx, 0);
        int transformed = 0, skipped = 0;

        for (int i = startIdx; i < rows.size(); i++) {
            String[] r = rows.get(i);
            if (r == null || r.length < 4) { skipped++; continue; }

            String idRaw   = r[0].trim();
            String nameRaw = r[1].trim();
            String priceRaw= r[2].trim();
            String catRaw  = r[3].trim();

            if (idRaw.isEmpty() || nameRaw.isEmpty() || priceRaw.isEmpty()) { skipped++; continue; }

            BigDecimal price;
            try {
                price = new BigDecimal(priceRaw);
            } catch (NumberFormatException ex) {
                skipped++; continue;
            }

            Product p = new Product(idRaw, nameRaw, price, catRaw);

            // Apply all rules in order (OO polymorphism)
            for (TransformerRule rule : rules) {
                rule.apply(p);
            }

            out.add(new String[]{
                p.getId(),
                p.getName(),
                p.getPrice().toPlainString(),
                p.getCategory(),
                p.getPriceRange()
            });
            transformed++;
        }
        return new TransformResult(out, read, transformed, skipped);
    }

    private static boolean looksLikeHeader(String[] row0) {
        if (row0 == null || row0.length < 4) return false;
        return row0[0].trim().equalsIgnoreCase("ProductID")
            && row0[1].trim().equalsIgnoreCase("Name")
            && row0[2].trim().equalsIgnoreCase("Price")
            && row0[3].trim().equalsIgnoreCase("Category");
    }
}

