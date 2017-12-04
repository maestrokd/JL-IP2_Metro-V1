package com.infoPulse.lessons.classesForMultiThreading;

import com.infoPulse.lessons.DatabaseTableClases.Driver;
import com.infoPulse.lessons.DatabaseTableClases.Train;
import com.infoPulse.lessons.DatabaseTableClases.TrainRun;

import java.util.Queue;

public class TravellerForTrain implements Runnable {

    // Fields
    private String name;
    private Train train;
    private Queue<Driver> driverQueue;


    // Constructors
    public TravellerForTrain(String name, Train train, Queue<Driver> driverQueue) {
        this.name = name;
        this.train = train;
        this.driverQueue = driverQueue;
    }


    @Override
    public void run() {
        new TrainRun(train, driverQueue);
    }
}
