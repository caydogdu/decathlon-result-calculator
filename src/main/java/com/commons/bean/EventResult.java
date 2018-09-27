package com.commons.bean;

public class EventResult {

    private Event event;

    // for calculating point
    private double result;

    // for writing to xml file
    private String originalResult;

    private boolean noResult;

    public Event getEvent() {
        return event;
    }

    public String getOriginalResult() {
        return originalResult;
    }

    public double getResult() {
        return result;
    }

    public boolean isNoResult() {
        return noResult;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public void setNoResult(boolean noResult) {
        this.noResult = noResult;
    }

    public void setOriginalResult(String originalResult) {
        this.originalResult = originalResult;
    }

    public void setResult(double result) {
        this.result = result;
    }

}
