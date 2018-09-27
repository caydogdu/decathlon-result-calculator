package com.commons;

import java.util.List;

import org.junit.Test;

import com.commons.bean.Ranking;
import com.commons.calculator.DecathlonResultCalculator;
import com.commons.calculator.ResultCalculator;
import com.commons.reader.CSVReader;
import com.commons.reader.DecathlonResultReader;
import com.commons.reader.Reader;
import com.commons.writer.DecathlonRankingsXMLWriter;
import com.commons.writer.RankingsXMLWriter;

public class DecathlonRankingsXMLWriterTest {

    private RankingsXMLWriter decathlonRankingsXMLWriter;

    @Test
    public void writeXMLTest() throws Exception {

        Reader reader = new CSVReader("results.csv");
        DecathlonResultReader resultReader = new DecathlonResultReader(reader);
        ResultCalculator decathlonResultCalculator = new DecathlonResultCalculator(resultReader);

        List<Ranking> rankings = decathlonResultCalculator.calculateRankings();

        decathlonRankingsXMLWriter = new DecathlonRankingsXMLWriter("rankings.xml", rankings);
        decathlonRankingsXMLWriter.writeRankingsXML();

    }

}
