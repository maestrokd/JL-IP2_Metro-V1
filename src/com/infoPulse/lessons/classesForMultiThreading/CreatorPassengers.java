package com.infoPulse.lessons.classesForMultiThreading;

import com.infoPulse.lessons.DatabaseTableClases.Passenger;
import com.infoPulse.lessons.DatabaseTableClases.Station;

import java.util.Random;

public class CreatorPassengers implements Runnable {

    // Fields

    Random random = new Random();
    private int f;

    private Station station;


    public CreatorPassengers(Station station) {
        this.station = station;
    }


    @Override
    public void run() {
        Passenger passenger;
        while (true) {
            f = (random.nextInt(100));

//            System.out.println("r - " + f);

            if (f > 40) {

                synchronized (station.getPassengersAtHall()) {
                    passenger = new Passenger();
                    station.getPassengersAtHall().add(passenger);
//                    System.out.println(passenger.getName() + " came into the hall. " + station.getName());

                    station.getPassengersAtHall().notifyAll();
                }
            }

            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
