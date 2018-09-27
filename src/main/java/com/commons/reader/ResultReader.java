package com.commons.reader;

import java.util.List;

import com.commons.bean.Athlete;

/**
 *
 * @author caydogdu This is a interface for result reading
 */
public interface ResultReader {

    List<Athlete> getAthletesEventResults();

}
