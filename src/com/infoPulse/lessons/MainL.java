package com.infoPulse.lessons;

import com.infoPulse.lessons.DaoObjects.*;
import com.infoPulse.lessons.DaoTools.*;
import com.infoPulse.lessons.DatabaseTableClases.*;

import java.util.NoSuchElementException;

public class MainL {
    public static void main(String[] args) throws NoSuchElementException {

        // Imitation of the metro without databases
//        metroWithoutDatabases();


        // Create some Tables in database
//        createTablesInDatabaseDDL();

        // Upload some results of Metro's work to some Tables in database for test(and for "Imitation of the metro with databases")
//        addTestDataToDatabase();


        // Imitation of the metro with databases
        metroWithDatabases();

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





        // TODO in other method and refactor
        for (Line line : metro.getLines()) {

            System.out.print(line.getName() + "|");
            for (TrainRun trainRun : line.getTrainRuns()) {
                System.out.print(trainRun.getTrain().getName() + " - " + trainRun.getDriver().getName() + " - " + trainRun.getStartDate() + "|");
            }
            System.out.println();
//            System.out.println(line.getName() + "|" + line.getTrainRuns().get);

            for (Station station : line.getStations()) {

                System.out.print(line.getName() + "|");
                for (StationVisit stationVisit : station.getStationVisits()) {
                    System.out.print(stationVisit.getVisitDate() + " - "  + stationVisit.getStation().getName() + " - "  + stationVisit.getTrainRun().getTrain().getName() + "|");
                }
                System.out.println();
            }
        }



    }


    // Imitation of the metro with databases
    public static void metroWithDatabases() {

        // TODO in other method
        // Verifying the connection to the database
        if (ConnectionSql.getInstance().getConnectionSource() == null) {
            System.out.println("No Connection with database!!!");
            return;
        }

        DaoObject daoObject;
        MetroWithDatabase metro = new MetroWithDatabase();


        // Upload Wagons from database to Depot
        daoObject = new DaoWagon();
        metro.setDepot(new Depot(daoObject.getAll()));


        // Upload Trains from database to Metro
        daoObject = new DaoTrain();
        metro.getTrains().addAll(daoObject.getAll());


        // Collect the Trains from wagons in Depot
        metro.collectTrainsWithDatabase(metro.getDepot(), metro.getTrains());

        // Inspection of assembled trains
        metro.inspectionOfAssembledTrains(metro.getTrains());

        // Inspection of depot after Collect the Trains from wagons in Depot
        metro.inspectionOfDepot(metro.getDepot());

        metro.inspectionOfWagonsInDepot(metro.getDepot());


        // Upload Drivers from database
        daoObject = new DaoDriver();
        metro.getDriverQueue().addAll(daoObject.getAll());

        // List of Line names and number of Lines (number of names)
        String[] namesOfLines = {"RedLine", "GreenLine", "BlueLine"};

        // Create Lines and filling lines with trains
        metro.setLines(metro.createAndFillLinesWithDatabase(metro.getTrains(), namesOfLines));

        // Drivers travel on lines by trains
        metro.travelByStations(metro.getLines(), metro.getDriverQueue());

        // List of drivers' experience
        metro.showListOfDriverExperience(metro.getDriverQueue());

//        // Move Wagons from Train at the Lines to Depot
//        metro.moveWagonsToDepot(metro.getLines(), metro.getDepot());








        // TODO in other method
        // Print TrainRuns and StationVisits
        for (Line line : metro.getLines()) {

            System.out.print(line.getName() + "|");
            for (TrainRun trainRun : line.getTrainRuns()) {
                System.out.print(trainRun.getTrain_id() + " - " + trainRun.getDriver_id() + " - " + trainRun.getStartDate() + "|");
            }
            System.out.println();
//            System.out.println(line.getName() + "|" + line.getTrainRuns().get);

            for (Station station : line.getStations()) {

                System.out.print(line.getName() + "|");
                for (StationVisit stationVisit : station.getStationVisits()) {
                    System.out.print(stationVisit.getVisitDate() + " - "  + stationVisit.getStation_id() + "|");
                }
                System.out.println();
            }
        }


        // TODO delete this test after changing the table structure
        // For test changing the table structure
//        DaoObjectsDDL daoObjectsDDL = new DaoObjectsDDL();
//
//        daoObjectsDDL.dropTableTrainRun();
//        daoObjectsDDL.createTableTrainRun();
//
//        daoObjectsDDL.dropTableStationVisit();
//        daoObjectsDDL.createTableStationVisit();


        // TODO in other method
        // Update TrainRun in database
        DaoTrainRun daoTrainRun = new DaoTrainRun();
        for (Line line : metro.getLines()) {
            daoTrainRun.addAll(line.getTrainRuns());
//            daoTrainRun.updateAll(line.getTrainRuns());
        }


        // TODO in other method
        // Update StationVisit from Depot in database
        DaoStationVisit daoStationVisit = new DaoStationVisit();
        for (Line line : metro.getLines()) {
            for (Station station : line.getStations()) {
                daoStationVisit.addAll(station.getStationVisits());
//                daoStationVisit.updateAll(station.getStationVisits());
            }
        }

        updateClassesInDatabase(metro);


    }


    // Upload some results of Metro's work to some Tables in database
    public static void addTestDataToDatabase() {
        DaoObject daoObject;
        Metro metro = new Metro();

        // Create Drivers
        metro.createDrivers(10);

        // Upload results of Metro's work to Driver Table in database
        daoObject = new DaoDriver();
//        daoObject.deleteAll();
        daoObject.addAll(metro.getDriverQueue());


        metro.createWagonsInDepot(100);


        // Collect the Trains from wagons in Depot
        metro.collectTrains(metro.getDepot(), metro.getTrains());




        // List of Line names and number of Lines (number of names)
        String[] namesOfLines = {"RedLine", "GreenLine", "BlueLine"};

        // Create Lines and filling lines with trains
        metro.setLines(metro.createAndFillLines(metro.getTrains(), namesOfLines));


        // Upload results of Metro's work to Line Table in database
        daoObject = new DaoLine();
        daoObject.deleteAll();
        daoObject.addAll(metro.getLines());


        // TODO
        // Upload results of Metro's work to Passenger Table in database
        daoObject = new DaoPassenger();
        daoObject.deleteAll();
        for (Line line : metro.getLines()) {
            for (Station station : line.getStations())
            daoObject.addAll(station.getPassengers());
        }


        // Upload results of Metro's work to Station Table in database
        daoObject = new DaoStation();
        daoObject.deleteAll();
        for (Line line : metro.getLines()) {
            daoObject.addAll(line.getStations());
        }


        // Upload results of Metro's work to Train Table in database
        daoObject = new DaoTrain();
        daoObject.deleteAll();

        for (Line line : metro.getLines()) {
            daoObject.addAll(line.getTrains());
        }


        // Upload results of Metro's work to Wagon Table in database
        daoObject = new DaoWagon();
        daoObject.deleteAll();

        for (Line line : metro.getLines()) {
            for (Train train : line.getTrains()) {
                daoObject.addAll(train.getWagons());
            }
        }




//        // Create TrainRun Table in database
//        DaoObject daoObjectTrainRun = new DaoTrainRun();
//
//
//        // Create StationVisit Table in database
//        DaoObject daoObjectStationVisit = new DaoStationVisit();
//
//
//        for (Line line : metro.getLines()) {
//
//            // Upload results of Metro's work in TrainRun Table
//            daoObjectTrainRun.addAll(line.getTrainRuns());
//
//            for (Station station : line.getStations()) {
//
//                // Upload results of Metro's work in StationVisit Table
//                daoObjectStationVisit.addAll(station.getStationVisits());
//            }
//        }

    }


    // Create some Tables in database
    public static void createTablesInDatabaseDDL() {
        DaoObjectsDDL daoObjectsDDL = new DaoObjectsDDL();

        daoObjectsDDL.dropTableDriver();
        daoObjectsDDL.createTableDriver();

        daoObjectsDDL.dropTableLine();
        daoObjectsDDL.createTableLine();

        daoObjectsDDL.dropTablePassenger();
        daoObjectsDDL.createTablePassenger();

        daoObjectsDDL.dropTableStation();
        daoObjectsDDL.createTableStation();

        daoObjectsDDL.dropTableStationVisit();
        daoObjectsDDL.createTableStationVisit();

        daoObjectsDDL.dropTableTrain();
        daoObjectsDDL.createTableTrain();

        daoObjectsDDL.dropTableTrainRun();
        daoObjectsDDL.createTableTrainRun();

        daoObjectsDDL.dropTableWagon();
        daoObjectsDDL.createTableWagon();
    }


    public static void updateClassesInDatabase(MetroWithDatabase metroWithDatabase) {

        // TODO in other method
        // Update Drivers in database
        DaoDriver daoDriver = new DaoDriver();
        daoDriver.updateAll(metroWithDatabase.getDriverQueue());


//        // TODO in other method
//        // Update Wagons from Depot in database
//        DaoWagon daoWagon = new DaoWagon();
//        daoWagon.updateAll(metro.getDepot().getWagons());

    }

}
