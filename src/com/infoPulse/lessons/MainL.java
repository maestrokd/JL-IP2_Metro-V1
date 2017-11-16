package com.infoPulse.lessons;

import com.infoPulse.lessons.DaoTools.ConnectionSql;
import com.infoPulse.lessons.DaoTools.DaoDriver;
import com.infoPulse.lessons.DaoTools.DaoObject;
import com.infoPulse.lessons.DaoTools.DaoWagon;

import java.util.NoSuchElementException;

public class MainL {
    public static void main(String[] args) throws NoSuchElementException {

        // Imitation of the metro without databases
        metroWithoutDatabases();

        // Imitation of the metro with databases
//        metroWithDatabases();


    }

    // Imitation of the metro without databases
    public static void metroWithoutDatabases() {

        Metro metro = new Metro();

        metro.createWagonsInDepot(100);

        // Collect the Trains from wagons in Depot
        metro.collectTrains(metro.getDepot(), metro.getTrains());

        // Inspection of assembled trains
        metro.inspectionOfAssembledTrains(metro.getTrains());

        // Inspection of depot after Collect the Trains from wagons in Depot
        metro.inspectionOfDepot(metro.getDepot());

        metro.inspectionOfWagonsInDepot(metro.getDepot());

        // Create Drivers
        metro.createDrivers(10);

        // List of Line names and number of Lines (number of names)
        String[] namesOfLines = {"RedLine", "GreenLine", "BlueLine"};

        // Create Lines and filling lines with trains
        metro.setLines(metro.createAndFillLines(metro.getTrains(), namesOfLines));

        // Drivers travel on lines by trains
        metro.travelByStations(metro.getLines(), metro.getDriverQueue());

        // List of drivers' experience
        metro.showListOfDriverExperience(metro.getDriverQueue());


        // Move Wagons from Train at the Lines to Depot
        metro.moveWagonsToDepot(metro.getLines(), metro.getDepot());


        // Create some Tables in database and upload results of Metro's work
//        createTablesInDatabase(metro);

    }


    // Imitation of the metro with databases
    public static void metroWithDatabases() {

        // TODO in other method
        // Verifying the connection to the database
        if (ConnectionSql.getConnection() == null) {
            System.out.println("No Connection with database!!!");
            return;
        }

        DaoObject daoObject;
        Metro metro = new Metro();

//        metro.createWagonsInDepot(100);

        // Upload Wagons from database to Depot
        daoObject = new DaoWagon();
        metro.setDepot(new Depot(daoObject.getAll()));

        // Collect the Trains from wagons in Depot
        metro.collectTrains(metro.getDepot(), metro.getTrains());

        // Inspection of assembled trains
        metro.inspectionOfAssembledTrains(metro.getTrains());

        // Inspection of depot after Collect the Trains from wagons in Depot
        metro.inspectionOfDepot(metro.getDepot());

        metro.inspectionOfWagonsInDepot(metro.getDepot());


        // Create Drivers
//        metro.createDrivers(10);


        // Upload Drivers from database
        daoObject = new DaoDriver();
        metro.getDriverQueue().addAll(daoObject.getAll());

        // List of Line names and number of Lines (number of names)
        String[] namesOfLines = {"RedLine", "GreenLine", "BlueLine"};

        // Create Lines and filling lines with trains
        metro.setLines(metro.createAndFillLines(metro.getTrains(), namesOfLines));

        // Drivers travel on lines by trains
        metro.travelByStations(metro.getLines(), metro.getDriverQueue());

        // List of drivers' experience
        metro.showListOfDriverExperience(metro.getDriverQueue());

        // Move Wagons from Train at the Lines to Depot
        metro.moveWagonsToDepot(metro.getLines(), metro.getDepot());


        // TODO in other method
        // Update Drivers in database
        DaoDriver daoDriver = new DaoDriver();
        daoDriver.updateAll(metro.getDriverQueue());


        // TODO in other method
        // Update Wagons from Depot in database
        DaoWagon daoWagon = new DaoWagon();
        daoWagon.updateAll(metro.getDepot().getWagons());

    }


    // Create some Tables in database and upload results of Metro's work
    public static void createTablesInDatabase(Metro metro) {

        // Create Driver Tables in database and upload results of Metro's work
        DaoObject daoObject = new DaoDriver();
        daoObject.deleteAll();
        daoObject.addAll(metro.getDriverQueue());

        // Create Wagon Tables in database and upload results of Metro's work
        daoObject = new DaoWagon();
        daoObject.dropTable();
        daoObject.createTable();
        daoObject.deleteAll();
        daoObject.addAll(metro.getDepot().getWagons());
    }
}
