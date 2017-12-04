package com.infoPulse.lessons.testForMultiThreading;


public class PassengerTest {
    private static int count  = 1;

    // Field
    private String name;



    public PassengerTest() {
        this.name = "Passenger_" + count++;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
