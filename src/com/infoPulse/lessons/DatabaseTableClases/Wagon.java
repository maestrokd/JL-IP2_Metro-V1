package com.infoPulse.lessons.DatabaseTableClases;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.util.LinkedList;

@DatabaseTable(tableName = "wagon")
public class Wagon {

    // Fields

    private static int count = 1;
    public static final String firstType = "Head wagon";
    public static final String secondType = "Other wagon";
    public static final int maxSize = 330;

    @DatabaseField(generatedId = true, useGetSet = true)
    private int wagon_id;

    @DatabaseField(useGetSet = true)
    private int train_id;

    @DatabaseField(useGetSet = true)
    private String name;

    @DatabaseField(useGetSet = true)
    private String type;

    private Train train;

    private LinkedList<Passenger> passengers = new LinkedList<>();


    // Constructors
    public Wagon() {}

    public Wagon(int type) {
        this.name = "Wagon " + count++;
//        wagon_id = count-1;
        if (type > 65) {
            this.type = firstType;
        }
        else this.type = secondType;
    }


    // Getters and Setters
    public int getWagon_id() {
        return wagon_id;
    }

    public void setWagon_id(int wagon_id) {
        this.wagon_id = wagon_id;
    }

    public int getTrain_id() {
        return train_id;
    }

    public void setTrain_id(int train_id) {
        this.train_id = train_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Train getTrain() {
        return train;
    }

    public void setTrain(Train train) {
        this.train = train;
    }

    public LinkedList<Passenger> getPassengers() {
        return passengers;
    }

    public void setPassengers(LinkedList<Passenger> passengers) {
        this.passengers = passengers;
    }
}
