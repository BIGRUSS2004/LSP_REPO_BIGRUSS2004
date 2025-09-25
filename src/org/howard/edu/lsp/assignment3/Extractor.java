package org.howard.edu.lsp.assignment3;

import java.io.IOException;
import java.util.List;

/** Extracts raw CSV rows (split into fields) from a given path. */
public interface Extractor {
    List<String[]> extract(String path) throws IOException;
}

