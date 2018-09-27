package com.commons.calculator;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import com.commons.bean.Athlete;
import com.commons.bean.Event;
import com.commons.bean.EventPlace;
import com.commons.bean.EventResult;
import com.commons.bean.Ranking;
import com.commons.reader.ResultReader;

public class DecathlonResultCalculator implements ResultCalculator {

    private static final Logger LOGGER = Logger.getLogger(DecathlonResultCalculator.class.getName());

    private ResultReader resultReader;

    public DecathlonResultCalculator(ResultReader resultReader) {
        this.resultReader = resultReader;
    }

    @Override
    public List<Ranking> calculateRankings() {

        List<Athlete> athletes = resultReader.getAthletesEventResults();
        LOGGER.log(Level.INFO, "Calculating rankings");
        athletes.forEach(athlete -> athlete.setTotal(calculateTotalPerAthlete(athlete.getEventResults())));
        athletes.sort((o1, o2) -> Double.compare(o2.getTotal(), o1.getTotal()));

        return IntStream.range(0, athletes.size()).mapToObj(index -> new Ranking(index + 1, athletes.get(index)))
                .collect(Collectors.toList());
    }

    private double calculateTotalPerAthlete(List<EventResult> eventResults) {
        double total = 0;
        for (EventResult eventResult : eventResults) {
            Event event = eventResult.getEvent();
            EventPlace eventPlace = event.getEventPlace();
            if (!eventResult.isNoResult()) {
                total += eventPlace.calculatePoint(event.getConstantA(), event.getConstantB(), event.getConstantC(),
                        eventResult.getResult());
            }
        }
        return total;
    }

}
