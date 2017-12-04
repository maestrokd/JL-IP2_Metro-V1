package com.infoPulse.lessons.classesForMultiThreading;

import com.infoPulse.lessons.DatabaseTableClases.Passenger;
import com.infoPulse.lessons.DatabaseTableClases.Station;

import java.util.LinkedList;

public class Escalator implements Runnable {

    // Fields
    private String name;

    private LinkedList<Passenger> passengers = new LinkedList<>();

    private Station station;

    public Escalator(String name, Station station) {

        this.name = name;
        this.station = station;
    }


    @Override
    public void run() {

        Passenger passenger;
        while (true) {

//            System.out.println(name + " Start----------------------------------------------------");

            synchronized (station.getPassengersAtHall()) {
                if (station.getPassengersAtHall().isEmpty() && passengers.isEmpty()) {
                    try {
                        station.getPassengersAtHall().wait();

//                        System.out.println(name + " End----------------------------------------------------");

                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }



                    continue;
                } else if (!station.getPassengersAtHall().isEmpty()) {
                    passenger = station.getPassengersAtHall().poll();
                    passengers.add(passenger);
//                    System.out.println(passenger.getName() + " came in to " + this.name);



                }
            }

            synchronized (station.getPassengers()) {
                if (!passengers.isEmpty()) {
                    passenger = passengers.pollFirst();
                    station.getPassengers().add(passenger);
//                    System.out.println(passenger.getName() + " came out from " + this.name);

//                    System.out.println(name + " End----------------------------------------------------");
//                    System.out.println(station.getName() + ": " + station.getPassengers().size());

                } else {
//                    System.out.println(name + " End----------------------------------------------------");
                }
            }

            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }



        }
    }

}
