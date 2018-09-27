package com.commons.bean;

import java.util.List;

public class Athlete {

    private String name;

    private List<EventResult> eventResults;

    private double total;

    public List<EventResult> getEventResults() {
        return eventResults;
    }

    public String getName() {
        return name;
    }

    public double getTotal() {
        return total;
    }

    public void setEventResults(List<EventResult> eventResults) {
        this.eventResults = eventResults;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setTotal(double total) {
        this.total = total;
    }

}
