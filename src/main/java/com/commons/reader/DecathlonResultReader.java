package com.commons.reader;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.commons.bean.Athlete;
import com.commons.bean.Event;
import com.commons.bean.EventResult;
import com.commons.bean.Field;
import com.commons.bean.Measurement;
import com.commons.bean.Track;

public class DecathlonResultReader implements ResultReader {

    private static final Logger LOGGER = Logger.getLogger(DecathlonResultReader.class.getName());

    private final Event[] events = new Event[10];

    private final List<String> noData = Arrays.asList("", "DNS", "DNF", "NM", "NQ");

    private Reader reader;

    public DecathlonResultReader(Reader reader) {
        this.reader = reader;
        initEvents();
    }

    /**
     *
     * @param resultInMinutes
     * @return result in seconds
     */
    private double convertToSeconds(String resultInMinutes) {
        int index = resultInMinutes.indexOf('.');
        String minutes = resultInMinutes.substring(0, index);
        String seconds = resultInMinutes.substring(index + 1, resultInMinutes.length());
        return Double.parseDouble(minutes) * 60 + Double.parseDouble(seconds);
    }

    /**
     *
     */
    @Override
    public List<Athlete> getAthletesEventResults() {

        List<Athlete> athletesEventResults = new ArrayList<>();
        List<List<String>> resultsMatrix = null;
        try {
            resultsMatrix = this.reader.getFileInput();// read given file input
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "Error reading results");
        }
        LOGGER.log(Level.INFO, "Reading athletes results");

        if (resultsMatrix != null) {
            for (List<String> line : resultsMatrix) {
                athletesEventResults.add(toAthleteWithResults(line));
            }
        }

        return athletesEventResults;

    }

    private void initEvents() {
        events[0] = new Event("100 metres", new Track(), 25.4347, 18, 1.81, Measurement.DEFAULT);
        events[1] = new Event("Long jump", new Field(), 0.14354, 220, 1.4, Measurement.TO_CENTIMETERS);
        events[2] = new Event("Shot put", new Field(), 51.39, 1.5, 1.05, Measurement.DEFAULT);
        events[3] = new Event("High jump", new Field(), 0.8465, 75, 1.42, Measurement.TO_CENTIMETERS);
        events[4] = new Event("400 metres", new Track(), 1.53775, 82, 1.81, Measurement.DEFAULT);
        events[5] = new Event("110 metres hurdles", new Track(), 5.74352, 28.5, 1.92, Measurement.DEFAULT);
        events[6] = new Event("Discuss throw", new Field(), 12.91, 4, 1.1, Measurement.DEFAULT);
        events[7] = new Event("Pole vault", new Field(), 0.2797, 100, 1.35, Measurement.TO_CENTIMETERS);
        events[8] = new Event("Javelin throw", new Field(), 10.14, 7, 1.08, Measurement.DEFAULT);
        events[9] = new Event("1500 metres", new Track(), 0.03768, 480, 1.85, Measurement.TO_SECONDS);
    }

    /**
     *
     * @param line
     * @return an athlete with results
     */
    private Athlete toAthleteWithResults(List<String> line) {

        Athlete athlete = new Athlete();
        List<EventResult> eventResults = new ArrayList<>();

        // this is the first column in the csv file
        athlete.setName(line.get(0));
        for (int i = 1; i < line.size(); i++) {
            EventResult eventResult = new EventResult();
            eventResult.setEvent(events[i - 1]);

            if (noData.contains(line.get(i))) {
                eventResult.setNoResult(true);
            } else {
                if (eventResult.getEvent().getMeasurement() == Measurement.TO_CENTIMETERS) {
                    // if event is jump event we must multiply result with 100
                    eventResult.setResult(Double.parseDouble(line.get(i)) * 100);
                } else if (eventResult.getEvent().getMeasurement() == Measurement.TO_SECONDS) {
                    // if event is 1500 metres event we must convert result to seconds
                    eventResult.setResult(convertToSeconds(line.get(i)));
                } else {
                    eventResult.setResult(Double.parseDouble(line.get(i)));
                }
                eventResult.setOriginalResult(line.get(i));
            }
            eventResults.add(eventResult);
        }
        athlete.setEventResults(eventResults);

        return athlete;

    }

}
