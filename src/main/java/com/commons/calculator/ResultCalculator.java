package com.commons.calculator;

import java.util.List;

import com.commons.bean.Ranking;

/**
 *
 * @author caydogdu This is a interface for result calculating
 */
public interface ResultCalculator {

    public List<Ranking> calculateRankings();

}
