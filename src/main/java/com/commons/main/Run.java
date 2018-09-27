package com.commons.main;

import java.util.List;

import com.commons.bean.Ranking;
import com.commons.calculator.DecathlonResultCalculator;
import com.commons.calculator.ResultCalculator;
import com.commons.reader.CSVReader;
import com.commons.reader.DecathlonResultReader;
import com.commons.reader.Reader;
import com.commons.reader.ResultReader;
import com.commons.writer.DecathlonRankingsXMLWriter;
import com.commons.writer.RankingsXMLWriter;

public class Run {

    public static void main(String[] args) {

        Reader reader = new CSVReader("results.csv");
        ResultReader resultReader = new DecathlonResultReader(reader);
        ResultCalculator resultCalculator = new DecathlonResultCalculator(resultReader);
        List<Ranking> rankings = resultCalculator.calculateRankings();

        RankingsXMLWriter writer = new DecathlonRankingsXMLWriter("rankings.xml", rankings);
        writer.writeRankingsXML();

    }

}
