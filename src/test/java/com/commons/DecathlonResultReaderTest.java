package com.commons;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;

import com.commons.bean.Athlete;
import com.commons.reader.CSVReader;
import com.commons.reader.DecathlonResultReader;
import com.commons.reader.Reader;
import com.commons.reader.ResultReader;

public class DecathlonResultReaderTest {

    private ResultReader decathlonResultReader;

    @Test
    public void getAthletesEventResultsTest() throws Exception {

        Reader reader = new CSVReader("results.csv");
        decathlonResultReader = new DecathlonResultReader(reader);

        List<Athlete> athletes = decathlonResultReader.getAthletesEventResults();
        assertEquals(4, athletes.size());

    }

    @Test
    public void getAthletesEventResultsTestFileNotFound() throws Exception {

        Reader reader = new CSVReader("results_.csv");
        decathlonResultReader = new DecathlonResultReader(reader);

        List<Athlete> athletes = decathlonResultReader.getAthletesEventResults();
        assertEquals(0, athletes.size());

    }

    @Test
    public void getAthletesEventResultsTestWithNAData() throws Exception {

        Reader reader = new CSVReader("resultsWithNAData.csv");
        decathlonResultReader = new DecathlonResultReader(reader);

        List<Athlete> athletes = decathlonResultReader.getAthletesEventResults();
        assertEquals(4, athletes.size());

    }

}
