package com.infoPulse.lessons;

import com.infoPulse.lessons.DatabaseTableClases.*;
import com.infoPulse.lessons.classesForMultiThreading.CreatorPassengers;
import com.infoPulse.lessons.classesForMultiThreading.Escalator;
import com.infoPulse.lessons.classesForMultiThreading.TravellerForLine;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

public class MetroWithoutDatabaseMultiThread {

    // Fields
    private Depot depot;
    private LinkedList<Line> lines;
    private LinkedList<Train> trains = new LinkedList<>();

    private Comparator<Driver> comparator = new Comparator<Driver>() {
        @Override
        public int compare(Driver o1, Driver o2) {
            if (o1.getExperience() > o2.getExperience()) {
                return -1;
            }
            if (o1.getExperience() < o2.getExperience()) {
                return 1;
            }
            return 0;
        }
    };
    private Queue<Driver> driverQueue = new PriorityQueue<>(comparator);


    public MetroWithoutDatabaseMultiThread() {


        depot = MetroFactory.createWagonsInDepot(100);

        // Collect the Trains from wagons in Depot
        trains = MetroFactory.collectTrains(depot);


        // Inspection of assembled trains
        inspectionOfAssembledTrains(trains);

        // Inspection of depot after Collect the Trains from wagons in Depot
        inspectionOfDepot(depot);

        inspectionOfWagonsInDepot(depot);


        // List of Line names and number of Lines (number of names)
        String[] namesOfLines = {"RedLine", "GreenLine", "BlueLine"};
        // Create Lines and filling lines with trains
        lines = MetroFactory.createAndFillLines(trains, namesOfLines);
    }


    // Getters and Setters
    public Depot getDepot() {
        return depot;
    }

    public void setDepot(Depot depot) {
        this.depot = depot;
    }

    public LinkedList<Line> getLines() {
        return lines;
    }

    public void setLines(LinkedList<Line> lines) {
        this.lines = lines;
    }

    public LinkedList<Train> getTrains() {
        return trains;
    }

    public void setTrains(LinkedList<Train> trains) {
        this.trains = trains;
    }

    public Queue<Driver> getDriverQueue() {
        return driverQueue;
    }

    public void setDriverQueue(Queue<Driver> driverQueue) {
        this.driverQueue = driverQueue;
    }


    // Methods




    // Inspection of depot
    public void inspectionOfDepot(Depot depot) {
        System.out.println("---------------------------------------");
        System.out.println("Number of wagons returned to the depot: " + depot.getWagonsReturnToDepot().size());
        System.out.println("Number of wagons in the depot after the formation of trains: " + depot.getWagons().size());
        System.out.println("---------------------------------------");
    }

    // Inspection of the wagons in the depot
    public void inspectionOfWagonsInDepot(Depot depot) {
        System.out.println("---------------------------------------");
        System.out.println(depot.getWagons().size() + " wagons in depot:\n");
        for (Wagon wagon : depot.getWagons()) {
            System.out.println(wagon.getType());
        }
        System.out.println("---------------------------------------");
    }

    // Inspection of assembled trains
    public void inspectionOfAssembledTrains(LinkedList<Train> trains) {
        System.out.println("---------------------------------------");
        System.out.println("Number of assembled trains: " + trains.size());
        for (Train train : trains) {
            System.out.println();
            train.getInfo();
        }
        System.out.println("\n---------------------------------------");
    }


    public void createDrivers(int numberOfDrivers) {
        System.out.println("---------------------------------------");
        for (int i = 0; i < numberOfDrivers; i++) {
            driverQueue.add(new Driver("Driver_" + (i+1), 0));
        }
        System.out.println("Number of drivers created: " + driverQueue.size());
        System.out.println("---------------------------------------");
    }

    // List of drivers' experience
    public void showListOfDriverExperience(Queue<Driver> driverQueue) {
        System.out.println("---------------------------------------");
        System.out.println("List of Driver experience:");
        Queue<Driver> driverQueueTemp = new PriorityQueue<>(driverQueue);

        System.out.println("Driver Name | Experience");
        while (!driverQueueTemp.isEmpty()) {
            Driver driverTemp = driverQueueTemp.remove();
            System.out.println(driverTemp.getName() + "|" + driverTemp.getExperience());
        }
        System.out.println("---------------------------------------");
    }



    // Travel by stations
    public void travelByStations(LinkedList<Line> lines, Queue<Driver> driverQueue) {
        System.out.println("---------------------------------------");

        for (Line line : lines) {
            System.out.println("______________");
            System.out.println("Line " + line.getName() + " Number of trains: " + line.getTrains().size());

            for (Train train : line.getTrains()) {
                new TrainRun(train, driverQueue);
            }
            System.out.println("______________");
        }
        System.out.println("---------------------------------------");
    }


    // Travel by stations with multithread
    public void travelMulti(LinkedList<Line> lines, Queue<Driver> driverQueue) {

        LinkedList<Thread> threadsCreators = new LinkedList<>();
        LinkedList<Thread> threadsEscalators = new LinkedList<>();
        LinkedList<Thread> threadsTravellersL = new LinkedList<>();

        for (Line line : lines) {
            for (Station station: line.getStations()) {
                if (!line.getStations().getLast().equals(station)){
                CreatorPassengers creatorPassengers = new CreatorPassengers(station);
                Escalator escalator1 = new Escalator(line.getName() + "/" + station.getName() + "/" + "escalator_1", station);
                Escalator escalator2 = new Escalator(line.getName() + "/" + station.getName() + "/" + "escalator_2", station);
                Escalator escalator3 = new Escalator(line.getName() + "/" + station.getName() + "/" + "escalator_3", station);

                Thread creatorPass = new Thread(creatorPassengers);
                Thread escalat1 = new Thread(escalator1);
                Thread escalat2 = new Thread(escalator2);
                Thread escalat3 = new Thread(escalator3);

                creatorPass.start();
                escalat1.start();
                escalat2.start();
                escalat3.start();

                threadsCreators.add(creatorPass);
                threadsEscalators.add(escalat1);
                threadsEscalators.add(escalat2);
                threadsEscalators.add(escalat3);

                }
            }
        }



        System.out.println("---------------------------------------");

        for (Line line : lines) {
//            System.out.println("______________");
            System.out.println("Line " + line.getName() + " Number of trains: " + line.getTrains().size());

            TravellerForLine travellerForLine = new TravellerForLine(line.getName() + "/" + "travellerL", line, driverQueue);
            Thread travellerL = new Thread(travellerForLine);
            travellerL.start();
            threadsTravellersL.add(travellerL);


//            for (Train train : line.getTrains()) {
//                new TrainRun(train, driverQueue);
//            }
//            System.out.println("______________");
        }



        // TODO Ending program if metro will be stop
        for (Thread thread : threadsTravellersL) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        for (Thread thread : threadsCreators) {
            thread.stop();
        }

        for (Thread thread : threadsEscalators) {
            thread.stop();
        }

        System.out.println(threadsTravellersL.size() + "|" + threadsCreators.size() + "|" + threadsEscalators.size());

        System.out.println("---------------------------------------");


    }


    // Move Wagons from Train at the Lines to Depot
    public void moveWagonsToDepot(LinkedList<Line> lines, Depot depot) {
        System.out.println("---------------------------------------");
        System.out.println("Depot size before moving wagons to Depot: " + depot.getWagons().size());
        for (Line line : lines) {
            for (Train train : line.getTrains()) {
                depot.getWagons().addAll(train.getWagons());
                train.setWagons(null);
            }
        }
        System.out.println("Depot size after moving wagons to Depot: " + depot.getWagons().size());
        System.out.println("---------------------------------------");
    }

}
