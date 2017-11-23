package com.infoPulse.lessons.DatabaseTableClases;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.util.LinkedList;

@DatabaseTable(tableName = "station")
public class Station {


    // Fields

    @DatabaseField(generatedId = true, useGetSet = true)
    private int station_id;

    @DatabaseField(useGetSet = true)
    private int line_id;

    @DatabaseField(useGetSet = true)
    private String name;

    private Line line;

    private LinkedList<Passenger> passengers = new LinkedList<>();

    private LinkedList<StationVisit> stationVisits = new LinkedList<>();


    // Constructors
    public Station() {}

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

    public Line getLine() {
        return line;
    }

    public void setLine(Line line) {
        this.line = line;
    }

    public LinkedList<Passenger> getPassengers() {
        return passengers;
    }

    public void setPassengers(LinkedList<Passenger> passengers) {
        this.passengers = passengers;
    }

    public LinkedList<StationVisit> getStationVisits() {
        return stationVisits;
    }

    public void setStationVisits(LinkedList<StationVisit> stationVisits) {
        this.stationVisits = stationVisits;
    }

    // Methods
    public String getInfo() {
        return line.getName() + ":" + name;
    }
}
