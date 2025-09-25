package org.howard.edu.lsp.assignment3;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

/** Concrete loader that writes CSV rows to a file (overwrites, ensures parent dir). */
public class CSVLoader implements Loader {
    @Override
    public void load(String path, List<String[]> rows) throws IOException {
        File outFile = new File(path);
        File parent = outFile.getParentFile();
        if (parent != null) parent.mkdirs();
        try (PrintWriter pw = new PrintWriter(new FileWriter(outFile, false))) {
            for (String[] row : rows) {
                pw.println(String.join(",", row));
            }
        }
    }
}

