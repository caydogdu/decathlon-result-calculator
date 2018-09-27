package com.commons.bean;

public class Event {

    // event name
    private String name;

    // event place Track, Field
    private EventPlace eventPlace;

    // these are calculation parameters

    private double constantA;

    private double constantB;

    private double constantC;

    private Measurement measurement;

    public Event(String name, EventPlace eventPlace, double constantA, double constantB, double constantC,
            Measurement measurement) {
        this.name = name;
        this.eventPlace = eventPlace;
        this.constantA = constantA;
        this.constantB = constantB;
        this.constantC = constantC;
        this.measurement = measurement;
    }

    public double getConstantA() {
        return constantA;
    }

    public double getConstantB() {
        return constantB;
    }

    public double getConstantC() {
        return constantC;
    }

    public EventPlace getEventPlace() {
        return eventPlace;
    }

    public Measurement getMeasurement() {
        return measurement;
    }

    public String getName() {
        return name;
    }

    public void setConstantA(double constantA) {
        this.constantA = constantA;
    }

    public void setConstantB(double constantB) {
        this.constantB = constantB;
    }

    public void setConstantC(double constantC) {
        this.constantC = constantC;
    }

    public void setEventPlace(EventPlace eventPlace) {
        this.eventPlace = eventPlace;
    }

    public void setMeasurement(Measurement measurement) {
        this.measurement = measurement;
    }

    public void setName(String name) {
        this.name = name;
    }

}
