package com.commons;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;

import com.commons.bean.Ranking;
import com.commons.calculator.DecathlonResultCalculator;
import com.commons.calculator.ResultCalculator;
import com.commons.reader.CSVReader;
import com.commons.reader.DecathlonResultReader;
import com.commons.reader.Reader;

public class DecathlonResultCalculatorTest {

    private ResultCalculator decathlonResultCalculator;

    @Test
    public void calculateRankingsTest() throws Exception {

        Reader reader = new CSVReader("results.csv");
        DecathlonResultReader resultReader = new DecathlonResultReader(reader);
        decathlonResultCalculator = new DecathlonResultCalculator(resultReader);

        List<Ranking> rankings = decathlonResultCalculator.calculateRankings();
        assertEquals(4, rankings.size());

    }

    @Test
    public void calculateRankingsTestFileNotFound() throws Exception {

        Reader reader = new CSVReader("results_.csv");
        DecathlonResultReader resultReader = new DecathlonResultReader(reader);
        decathlonResultCalculator = new DecathlonResultCalculator(resultReader);

        List<Ranking> rankings = decathlonResultCalculator.calculateRankings();
        assertEquals(0, rankings.size());

    }

    @Test
    public void calculateRankingsTestWithNAData() throws Exception {

        Reader reader = new CSVReader("resultsWithNAData.csv");
        DecathlonResultReader resultReader = new DecathlonResultReader(reader);
        decathlonResultCalculator = new DecathlonResultCalculator(resultReader);

        List<Ranking> rankings = decathlonResultCalculator.calculateRankings();
        assertEquals(4, rankings.size());

    }

}
