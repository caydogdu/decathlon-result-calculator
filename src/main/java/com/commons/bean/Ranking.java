package com.commons.bean;

public class Ranking {

    private int number;

    private Athlete athlete;

    public Ranking(int number, Athlete athlete) {
        this.number = number;
        this.athlete = athlete;
    }

    public Athlete getAthlete() {
        return athlete;
    }

    public int getNumber() {
        return number;
    }

    public void setAthlete(Athlete athlete) {
        this.athlete = athlete;
    }

    public void setNumber(int number) {
        this.number = number;
    }

}
