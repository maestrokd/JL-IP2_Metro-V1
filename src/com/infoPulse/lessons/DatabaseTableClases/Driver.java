package com.infoPulse.lessons.DatabaseTableClases;


import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.util.Random;

@DatabaseTable(tableName = "driver")
public class Driver {

    // Fields

    @DatabaseField(generatedId = true, useGetSet = true)
    private  int driver_id;

    @DatabaseField(useGetSet = true)
    private String name;

    @DatabaseField(useGetSet = true)
    private float experience;




    // Constructors
    public Driver() {}

    public Driver(String name, float experience)  {

        this.name = name;
        this.experience = experience;
    }


    // Getters and Setters
    public int getDriver_id() {
        return driver_id;
    }

    public void setDriver_id(int driver_id) {
        this.driver_id = driver_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getExperience() {
        return experience;
    }

    public void setExperience(float experience) {
        this.experience = experience;
    }


    // Methods
    public void addExperience() {

        // TODO Random in other class
        Random random = new Random();
        experience = experience + (random.nextInt(100)-50);
    }
}
