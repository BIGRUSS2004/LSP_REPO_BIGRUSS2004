package org.howard.edu.lsp.assignment3;

import java.math.BigDecimal;

/**
 * Domain model for a product row flowing through the ETL.
 * Encapsulates id, name, price, and category; priceRange is derived at transform time.
 */
public class Product {
    private final String id;
    private String name;
    private BigDecimal price;           // always kept non-null once parsed
    private String category;            // may be updated by rules
    private final String originalCategory; // for rules that depend on original value
    private String priceRange;          // derived output: Low/Medium/High/Premium

    public Product(String id, String name, BigDecimal price, String category) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.category = category == null ? "" : category;
        this.originalCategory = this.category;
        this.priceRange = "";
    }

    public String getId() { return id; }
    public String getName() { return name; }
    public BigDecimal getPrice() { return price; }
    public String getCategory() { return category; }
    public String getOriginalCategory() { return originalCategory; }
    public String getPriceRange() { return priceRange; }

    public void setName(String name) { this.name = name; }
    public void setPrice(BigDecimal price) { this.price = price; }
    public void setCategory(String category) { this.category = category; }
    public void setPriceRange(String priceRange) { this.priceRange = priceRange; }
}

