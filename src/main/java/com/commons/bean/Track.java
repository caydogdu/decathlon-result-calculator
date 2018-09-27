package com.commons.bean;

public class Track implements EventPlace {

    @Override
    public double calculatePoint(double constantA, double constantB, double constantC, double result) {
        return (int) (constantA * Math.pow(constantB - result, constantC));
    }

}
