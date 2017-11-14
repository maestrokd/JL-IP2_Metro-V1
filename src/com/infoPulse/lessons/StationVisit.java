package com.infoPulse.lessons;

import java.util.Random;

public class StationVisit {


    public StationVisit(Train train, Station station) {

        if (train.getLine().stations.getLast().equals(station)) {

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
            station.passengers.add(new Passenger());
        }

        System.out.println();
        System.out.println(station.getInfo() + " | " + station.passengers.size() + " passengers are ready to go");

        for (Wagon wagon : train.getWagons()) {
            System.out.print(wagon.getName() + " | " + wagon.passengers.size() + " | ");


            // Moving the passengers from the Wagon
            if (wagon.passengers.size() != 0) {
                numberOfPassengersToOut = random.nextInt(wagon.passengers.size());

//                wagon.passengers.removeAll(wagon.passengers.subList(0, numberOfPassengersToOut));
//                countPassenger = 0;
                for (int i = 0; i < numberOfPassengersToOut; i++) {
                    wagon.passengers.remove();
//                    countPassenger++;
                }

                System.out.print(numberOfPassengersToOut + " -->> out | ");
            } else {
                System.out.print("0 -->> out | ");
            }


            // Moving the passengers to the Wagon
            countPassenger = 0;
            while ((wagon.passengers.size() < Wagon.maxSize) && (!station.passengers.isEmpty())) {
                wagon.passengers.add(station.passengers.pollFirst());
                countPassenger++;
            }
            System.out.print("in <<-- " + countPassenger + " | ");
            System.out.println(wagon.getName() + " | " + wagon.passengers.size());
        }
        System.out.println(station.getInfo() + " | " + station.passengers.size() + " passengers left at the station");
    }


    // If station is last
    public void lastStationVisit(Train train, Station station) {

        int numberOfPassengersToOut;

        System.out.println();
        System.out.println(station.getInfo() + " | Last station! |" + station.passengers.size() + " passengers who finished the trip");

        for (Wagon wagon : train.getWagons()) {
            System.out.print(wagon.getName() + " | " + wagon.passengers.size() + " | ");


            // Moving the passengers from the Wagon
            if (wagon.passengers.size() != 0) {
                numberOfPassengersToOut = wagon.passengers.size();
                station.passengers.addAll(wagon.passengers);
                wagon.passengers.clear();
                System.out.print(numberOfPassengersToOut + " -->> out | ");
            } else {
                System.out.print("0 -->> out | ");
            }
            System.out.println(wagon.getName() + " | " + wagon.passengers.size());
        }
        System.out.println(station.getInfo() + " | " + station.passengers.size() + " passengers who finished the trip");
    }

}
