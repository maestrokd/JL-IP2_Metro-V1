package com.infoPulse.lessons;

import java.util.LinkedList;

public class Station {


    // Fields
    private int station_id;
    private String name;

    private int line_id;
    private Line line;

    LinkedList<Passenger> passengers = new LinkedList<>();


    // Constructors
    public Station(String name, Line line) {
        this.name = name;
        this.line = line;
    }


    // Getters and Setters
    public int getStation_id() {
        return station_id;
    }

    public void setStation_id(int station_id) {
        this.station_id = station_id;
    }


    // Methods
    public String getInfo() {
        return line.getName() + ":" + name;
    }
}
