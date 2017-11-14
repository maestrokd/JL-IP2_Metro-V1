package com.infoPulse.lessons;

import java.util.NoSuchElementException;

public class MainL {
    public static void main(String[] args) throws NoSuchElementException {

        Metro metro = new Metro();

        metro.createWagonsInDepot(100);

        // Collect the Trains from wagons in Depot
        metro.collectTrains(metro.getDepot(), metro.getTrains());

        // Inspection of assembled trains
        metro.inspectionOfAssembledTrains(metro.getTrains());

        // Inspection of depot after Collect the Trains from wagons in Depot
        metro.inspectionOfDepot(metro.getDepot());

        metro.inspectionOfWagonsInDepot(metro.getDepot());

        metro.createDrivers(10);

        // List of Line names and number of Lines (number of names)
        String[] namesOfLines = {"RedLine", "GreenLine", "BlueLine"};

        // Create Lines and filling lines with trains
        metro.setLines(metro.createAndFillLines(metro.getTrains(), namesOfLines));

        // Drivers travel on lines by trains
        metro.travelByStations(metro.getLines(), metro.getDriverQueue());

        // List of drivers' experience
        metro.showListOfDriverExperience(metro.getDriverQueue());

    }
}
