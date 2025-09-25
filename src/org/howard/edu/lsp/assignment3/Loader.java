package org.howard.edu.lsp.assignment3;

import java.io.IOException;
import java.util.List;

/** Loads CSV rows (including header) to a target path. */
public interface Loader {
    void load(String path, List<String[]> rows) throws IOException;
}

