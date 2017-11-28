package com.infoPulse.lessons;

import com.infoPulse.lessons.DaoObjectsV1.*;
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
//        metroWithDatabases();

        // Imitation of the metro with databases version 2
        metroWithDatabasesV2();




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

        DaoObjectV1 daoObjectV1;
        MetroWithDatabase metro = new MetroWithDatabase();


        // Upload Wagons from database to Depot
        daoObjectV1 = new DaoWagonV1();
        metro.setDepot(new Depot(daoObjectV1.getAll()));


        // Upload Trains from database to Metro
        daoObjectV1 = new DaoTrainV1();
        metro.getTrains().addAll(daoObjectV1.getAll());


        // Collect the Trains from wagons in Depot
        metro.collectTrainsWithDatabase(metro.getDepot(), metro.getTrains());

        // Inspection of assembled trains
        metro.inspectionOfAssembledTrains(metro.getTrains());

        // Inspection of depot after Collect the Trains from wagons in Depot
        metro.inspectionOfDepot(metro.getDepot());

        metro.inspectionOfWagonsInDepot(metro.getDepot());


        // Upload Drivers from database
        daoObjectV1 = new DaoDriverV1();
        metro.getDriverQueue().addAll(daoObjectV1.getAll());

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
        DaoTrainRunV1 daoTrainRun = new DaoTrainRunV1();
        for (Line line : metro.getLines()) {
            daoTrainRun.addAll(line.getTrainRuns());
//            daoTrainRun.updateAll(line.getTrainRuns());
        }


        // TODO in other method
        // Update StationVisit from Depot in database
        DaoStationVisitV1 daoStationVisit = new DaoStationVisitV1();
        for (Line line : metro.getLines()) {
            for (Station station : line.getStations()) {
                daoStationVisit.addAll(station.getStationVisits());
//                daoStationVisit.updateAll(station.getStationVisits());
            }
        }

        updateClassesInDatabase(metro);


    }


    // Imitation of the metro with databases V2 with DaoObjectV2 and DaoGenericV2
    public static void metroWithDatabasesV2() {


        // Verifying the connection to the database
        if (!isConnection()) {
            return;
        }

        DaoObjectV2 daoObjectV2;
        MetroWithDatabase metro = new MetroWithDatabase();


        // Upload Wagons from database to Depot
        daoObjectV2 = new DaoGenericV2(Wagon.class, Integer.class);
        metro.setDepot(new Depot(daoObjectV2.getAll()));


        // Upload Trains from database to Metro
        daoObjectV2 = new DaoGenericV2(Train.class, Integer.class);
        metro.getTrains().addAll(daoObjectV2.getAll());


        // Collect the Trains from wagons in Depot
        metro.collectTrainsWithDatabase(metro.getDepot(), metro.getTrains());

        // Inspection of assembled trains
        metro.inspectionOfAssembledTrains(metro.getTrains());

        // Inspection of depot after Collect the Trains from wagons in Depot
        metro.inspectionOfDepot(metro.getDepot());

        metro.inspectionOfWagonsInDepot(metro.getDepot());


        // Upload Drivers from database
        daoObjectV2 = new DaoGenericV2(Driver.class, Integer.class);
        metro.getDriverQueue().addAll(daoObjectV2.getAll());

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
        DaoTrainRunV1 daoTrainRun = new DaoTrainRunV1();
        for (Line line : metro.getLines()) {
            daoTrainRun.addAll(line.getTrainRuns());
//            daoTrainRun.updateAll(line.getTrainRuns());
        }


        // TODO in other method
        // Update StationVisit from Depot in database
        DaoStationVisitV1 daoStationVisit = new DaoStationVisitV1();
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
        DaoObjectV1 daoObjectV1;
        Metro metro = new Metro();

        // Create Drivers
        metro.createDrivers(10);

        // Upload results of Metro's work to Driver Table in database
        daoObjectV1 = new DaoDriverV1();
//        daoObjectV1.deleteAll();
        daoObjectV1.addAll(metro.getDriverQueue());


        metro.createWagonsInDepot(100);


        // Collect the Trains from wagons in Depot
        metro.collectTrains(metro.getDepot(), metro.getTrains());




        // List of Line names and number of Lines (number of names)
        String[] namesOfLines = {"RedLine", "GreenLine", "BlueLine"};

        // Create Lines and filling lines with trains
        metro.setLines(metro.createAndFillLines(metro.getTrains(), namesOfLines));


        // Upload results of Metro's work to Line Table in database
        daoObjectV1 = new DaoLineV1();
        daoObjectV1.deleteAll();
        daoObjectV1.addAll(metro.getLines());


        // TODO
        // Upload results of Metro's work to Passenger Table in database
        daoObjectV1 = new DaoPassengerV1();
        daoObjectV1.deleteAll();
        for (Line line : metro.getLines()) {
            for (Station station : line.getStations())
            daoObjectV1.addAll(station.getPassengers());
        }


        // Upload results of Metro's work to Station Table in database
        daoObjectV1 = new DaoStationV1();
        daoObjectV1.deleteAll();
        for (Line line : metro.getLines()) {
            daoObjectV1.addAll(line.getStations());
        }


        // Upload results of Metro's work to Train Table in database
        daoObjectV1 = new DaoTrainV1();
        daoObjectV1.deleteAll();

        for (Line line : metro.getLines()) {
            daoObjectV1.addAll(line.getTrains());
        }


        // Upload results of Metro's work to Wagon Table in database
        daoObjectV1 = new DaoWagonV1();
        daoObjectV1.deleteAll();

        for (Line line : metro.getLines()) {
            for (Train train : line.getTrains()) {
                daoObjectV1.addAll(train.getWagons());
            }
        }




//        // Create TrainRun Table in database
//        DaoObjectV1 daoObjectTrainRun = new DaoTrainRunV1();
//
//
//        // Create StationVisit Table in database
//        DaoObjectV1 daoObjectStationVisit = new DaoStationVisitV1();
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
        DaoDriverV1 daoDriver = new DaoDriverV1();
        daoDriver.updateAll(metroWithDatabase.getDriverQueue());


//        // TODO in other method
//        // Update Wagons from Depot in database
//        DaoWagonV1 daoWagon = new DaoWagonV1();
//        daoWagon.updateAll(metro.getDepot().getWagons());

    }


    // Verifying the connection to the database
    public static boolean isConnection() {
        if (ConnectionSql.getInstance().getConnectionSource() == null) {
            System.out.println("No Connection with database!!!");
            return false;
        }
        return true;
    }

}
