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

         do {

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

            // Wait when first train finish his travel
             try {
                 threadsTravellersT.getFirst().join();
             } catch (InterruptedException e) {
                 e.printStackTrace();
             }
             threadsTravellersT.clear();

            // Show number of passengers at the last station after the next travel of all trains along the line
             System.out.println(line.getStations().getLast().getPassengers().size() + " at the last station " + line.getName());
             System.out.println();

         } while (true);

//        for (Thread thread : threadsTravellersT) {
//            try {
//                thread.join();
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }
//
//        System.out.println("______________");

    }
}
