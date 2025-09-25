package org.howard.edu.lsp.assignment3;

import java.util.List;

/** Result bundle from transformation phase: output rows + counters. */
public class TransformResult {
    public final List<String[]> rows; // includes header
    public final int read;
    public final int transformed;
    public final int skipped;

    public TransformResult(List<String[]> rows, int read, int transformed, int skipped) {
        this.rows = rows;
        this.read = read;
        this.transformed = transformed;
        this.skipped = skipped;
    }
}

