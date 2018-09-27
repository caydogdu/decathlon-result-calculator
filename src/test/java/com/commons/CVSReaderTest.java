package com.commons;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.util.List;

import org.junit.Test;

import com.commons.reader.CSVReader;
import com.commons.reader.Reader;

public class CVSReaderTest {

    private Reader csvReader;

    @Test
    public void getFileInputTest() throws Exception {

        csvReader = new CSVReader("results.csv");
        List<List<String>> resultsMatrix = csvReader.getFileInput();
        assertEquals(4, resultsMatrix.size());

    }

    @Test
    public void getFileInputTestFileNotFound() throws Exception {

        csvReader = new CSVReader("results_.csv");
        List<List<String>> resultsMatrix = csvReader.getFileInput();
        assertNull(resultsMatrix);

    }

    @Test
    public void getFileInputTestWithNAData() throws Exception {

        csvReader = new CSVReader("resultsWithNAData.csv");
        List<List<String>> resultsMatrix = csvReader.getFileInput();
        assertEquals(4, resultsMatrix.size());

    }

}
