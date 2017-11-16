package com.infoPulse.lessons;

import java.util.Random;

public class StationVisit {


    // Constructors
    public StationVisit() {}

    public StationVisit(Train train, Station station) {

        if (train.getLine().getStations().getLast().equals(station)) {

            // If station is last
            lastStationVisit(train, station);
        } else {

            // If station not last
            stationVisit(train, station);
        }
    }


    // If station not last
    public void stationVisit(Train train, Station station) {

        int countPassenger;
        int numberOfPassengersToOut;
        Random random = new Random();


        // Create passengers in station
        for (int i = 0; i < (random.nextInt(400) + 800); i++) {
            station.getPassengers().add(new Passenger());
        }

        System.out.println();
        System.out.println(station.getInfo() + " | " + station.getPassengers().size() + " passengers are ready to go");

        for (Wagon wagon : train.getWagons()) {
            System.out.print(wagon.getName() + " | " + wagon.getPassengers().size() + " | ");


            // Moving the passengers from the Wagon
            if (wagon.getPassengers().size() != 0) {
                numberOfPassengersToOut = random.nextInt(wagon.getPassengers().size());

//                wagon.passengers.removeAll(wagon.passengers.subList(0, numberOfPassengersToOut));
//                countPassenger = 0;
                for (int i = 0; i < numberOfPassengersToOut; i++) {
                    wagon.getPassengers().remove();
//                    countPassenger++;
                }

                System.out.print(numberOfPassengersToOut + " -->> out | ");
            } else {
                System.out.print("0 -->> out | ");
            }


            // Moving the passengers to the Wagon
            countPassenger = 0;
            while ((wagon.getPassengers().size() < Wagon.maxSize) && (!station.getPassengers().isEmpty())) {
                wagon.getPassengers().add(station.getPassengers().pollFirst());
                countPassenger++;
            }
            System.out.print("in <<-- " + countPassenger + " | ");
            System.out.println(wagon.getName() + " | " + wagon.getPassengers().size());
        }
        System.out.println(station.getInfo() + " | " + station.getPassengers().size() + " passengers left at the station");
    }


    // If station is last
    public void lastStationVisit(Train train, Station station) {

        int numberOfPassengersToOut;

        System.out.println();
        System.out.println(station.getInfo() + " | Last station! |" + station.getPassengers().size() + " passengers who finished the trip");

        for (Wagon wagon : train.getWagons()) {
            System.out.print(wagon.getName() + " | " + wagon.getPassengers().size() + " | ");


            // Moving the passengers from the Wagon
            if (wagon.getPassengers().size() != 0) {
                numberOfPassengersToOut = wagon.getPassengers().size();
                station.getPassengers().addAll(wagon.getPassengers());
                wagon.getPassengers().clear();
                System.out.print(numberOfPassengersToOut + " -->> out | ");
            } else {
                System.out.print("0 -->> out | ");
            }
            System.out.println(wagon.getName() + " | " + wagon.getPassengers().size());
        }
        System.out.println(station.getInfo() + " | " + station.getPassengers().size() + " passengers who finished the trip");
    }

}
