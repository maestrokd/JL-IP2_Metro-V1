package com.infoPulse.lessons;

import java.util.LinkedList;

public class Line {


    // Fields
    private int line_id;
    private String name;

    LinkedList<Station> stations = new LinkedList<>();
    LinkedList<Train> trains = new LinkedList<>();


    // Constructors
    public Line(String name, int numberOfStations) {
        this.name = name;
//        this.line_id = line_id;
        for (int i = 0; i < numberOfStations; i++) {
            stations.add(new Station("Station_" + (i + 1), this));
        }
    }


    // Getters and Setters
    public int getLine_id() {
        return line_id;
    }

    public String getName() {
        return name;
    }
}
