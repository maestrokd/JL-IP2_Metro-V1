package com.infoPulse.lessons.classesForMultiThreading;

import com.infoPulse.lessons.DatabaseTableClases.Driver;
import com.infoPulse.lessons.DatabaseTableClases.Line;
import com.infoPulse.lessons.DatabaseTableClases.Train;
import com.infoPulse.lessons.DatabaseTableClases.TrainRun;

import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

public class TravellerForLine implements Runnable {

    // Fields
    private String name;
    private Line line;
    private Queue<Driver> driverQueue;

    // Constructors
    public TravellerForLine(String name, Line line, Queue<Driver> driverQueue) {
        this.name = name;
        this.line = line;
        this.driverQueue = driverQueue;
    }

    @Override
    public void run() {

        LinkedList<Thread> threadsTravellersT = new LinkedList<>();

        for (Train train : line.getTrains()) {
//            new TrainRun(train, driverQueue);
            TravellerForTrain travellerForTrain = new TravellerForTrain(train.getName() + "/" + "travellerT", train, driverQueue);
            Thread travellerT = new Thread(travellerForTrain);
            travellerT.start();

            threadsTravellersT.add(travellerT);

            try {
                Thread.sleep(4000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }

        for (Thread thread : threadsTravellersT) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println("______________");

    }
}
