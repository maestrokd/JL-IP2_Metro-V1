package com.infoPulse.lessons;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.util.LinkedList;

@DatabaseTable(tableName = "line")
public class Line {


    // Fields

    @DatabaseField(id = true, useGetSet = true)
    private int line_id;

    @DatabaseField(useGetSet = true)
    private String name;

    private LinkedList<Station> stations = new LinkedList<>();
    private LinkedList<Train> trains = new LinkedList<>();


    // Constructors
    public Line() {}

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

    public void setLine_id(int line_id) {
        this.line_id = line_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LinkedList<Station> getStations() {
        return stations;
    }

    public void setStations(LinkedList<Station> stations) {
        this.stations = stations;
    }

    public LinkedList<Train> getTrains() {
        return trains;
    }

    public void setTrains(LinkedList<Train> trains) {
        this.trains = trains;
    }
}
