package com.commons.reader;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CSVReader implements Reader {

    private static final Logger LOGGER = Logger.getLogger(CSVReader.class.getName());

    private String fileName;

    public CSVReader(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public List<List<String>> getFileInput() throws IOException {

        LOGGER.log(Level.INFO, "Reading file {0}", fileName);
        List<List<String>> resultsMatrix = null;
        InputStream inputFS = null;

        try {
            ClassLoader classLoader = getClass().getClassLoader();
            URL url = classLoader.getResource(fileName);
            if (url == null) {
                throw new FileNotFoundException();
            }
            File inputFile = new File(url.getFile());
            inputFS = new FileInputStream(inputFile);
            Object[] lines = null;
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputFS))) {
                lines = reader.lines().toArray();
            }
            resultsMatrix = new ArrayList<>();
            for (Object line : lines) {
                String[] items = ((String) line).split(";");
                if (items[0].length() < 1) { // for the last line
                    break;
                }
                resultsMatrix.add(Arrays.asList(items));
            }
            inputFS.close();
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Exception occur", e);
        } finally {
            if (inputFS != null) {
                inputFS.close();
            }
        }

        return resultsMatrix;
    }
}
