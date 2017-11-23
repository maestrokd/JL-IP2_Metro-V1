package com.infoPulse.lessons.DatabaseTableClases;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "passenger")
public class Passenger {

    // Fields
    @DatabaseField(generatedId = true, useGetSet = true)
    private int passenger_id;

    @DatabaseField(useGetSet = true)
    private int escalator_id;

    @DatabaseField(useGetSet = true)
    private int turniket_id;

    @DatabaseField(useGetSet = true)
    private int station_id;

    @DatabaseField(useGetSet = true)
    private int wagon_id;

    @DatabaseField(useGetSet = true)
    private String name;

    // Constructors
    public Passenger() {}

    // Getters and Setters
    public int getPassenger_id() {
        return passenger_id;
    }

    public void setPassenger_id(int passenger_id) {
        this.passenger_id = passenger_id;
    }

    public int getEscalator_id() {
        return escalator_id;
    }

    public void setEscalator_id(int escalator_id) {
        this.escalator_id = escalator_id;
    }

    public int getTurniket_id() {
        return turniket_id;
    }

    public void setTurniket_id(int turniket_id) {
        this.turniket_id = turniket_id;
    }

    public int getStation_id() {
        return station_id;
    }

    public void setStation_id(int station_id) {
        this.station_id = station_id;
    }

    public int getWagon_id() {
        return wagon_id;
    }

    public void setWagon_id(int wagon_id) {
        this.wagon_id = wagon_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
