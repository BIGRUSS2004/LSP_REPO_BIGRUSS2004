package org.howard.edu.lsp.assignment3;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Represents a single record (row) in the ETL pipeline.
 * This implementation stores columns in a LinkedHashMap to preserve CSV order.
 * 
 * <p>Using a flexible map-based model lets you keep Assignment 2's exact
 * headers/columns without hard-coding a schema. If your A2 used a strong-typed
 * Product class, you can swap this out for explicit fields and corresponding
 * getters/setters.</p>
 */
public class Product {
    private final LinkedHashMap<String, String> fields = new LinkedHashMap<>();

    /** Adds or updates a field value. */
    public void put(String key, String value) {
        fields.put(key, value);
    }

    /** Returns a field value or null if missing. */
    public String get(String key) {
        return fields.get(key);
    }

    /** Returns an ordered view of field entries. */
    public Iterable<Map.Entry<String, String>> entries() {
        return fields.entrySet();
    }

    /** Returns the ordered set of headers for this record. */
    public Iterable<String> headers() {
        return fields.keySet();
    }

    /** Returns the number of fields on this record. */
    public int size() {
        return fields.size();
    }
}
