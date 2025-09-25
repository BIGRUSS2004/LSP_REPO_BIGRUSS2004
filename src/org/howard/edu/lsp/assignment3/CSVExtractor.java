package org.howard.edu.lsp.assignment3;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/** Concrete extractor that reads CSV with "," delimiter and keeps empty fields. */
public class CSVExtractor implements Extractor {
    @Override
    public List<String[]> extract(String path) throws IOException {
        List<String[]> rows = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            String line;
            while ((line = br.readLine()) != null) {
                rows.add(line.split(",", -1));
            }
        }
        return rows;
    }
}

