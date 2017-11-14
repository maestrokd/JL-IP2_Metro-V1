package com.infoPulse.lessons;

import java.util.*;

public class Metro {

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

    public void createWagonsInDepot(int numberOfWagons) {
        depot = new Depot(numberOfWagons);
    }


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


    // Collect the Trains from wagons in Depot
    public void collectTrains(Depot depot, LinkedList<Train> trains) {
        System.out.println("---------------------------------------");
        System.out.println("Number of wagons in the depot before the formation of trains: " + depot.getWagons().size());
        while (true) {
            if (depot.getWagons().size() < Train.maxNumberOfSubwayCarsInTrain) {
                depot.getWagons().addAll(depot.getWagonsReturnToDepot());
                break;
            }
            trains.addLast(new Train());
            while (true) {

                if (depot.getWagons().size() == 0) {
                    try {
                        throw new DepotEmptyException();
                    } catch (DepotEmptyException e) {
//                       System.out.println("Error! The wagons of the required type in the depot are over!");


                        // Return of wagons of an unfinished train to the wagonsReturnToDepot
                        if (trains.getLast().getWagons().size() != Train.maxNumberOfSubwayCarsInTrain) {
                            for (Wagon wagon : trains.getLast().getWagons()) {
                                depot.getWagons().addLast(wagon);
                            }
                            trains.removeLast();
                        }
                        break;
                    }
                }

                trains.getLast().addWagon(depot.getWagons().removeFirst(), depot.getWagonsReturnToDepot());

                if (trains.getLast().getWagons().size() == Train.maxNumberOfSubwayCarsInTrain) {
                    break;
                }
            }
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
            driverQueue.add(new Driver(i + 1, "Driver_" + (i+1), 0));
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


    // Create Lines and distribution of Trains along the Line
    public LinkedList<Line> createAndFillLines(LinkedList<Train> trains, String[] namesOfLines) {
        LinkedList<Line> lines = new LinkedList<>();
        int numberOfLines = namesOfLines.length;

        // TODO numberOfStations
        int numberOfStations = 3;

        for (int i = 0; i < numberOfLines; i++) {
            lines.add(new Line(namesOfLines[i], numberOfStations));
        }

        while (!trains.isEmpty()) {
            for (Line line :lines) {
                if (!trains.isEmpty()) {
                    line.trains.add(trains.pollFirst());
                    line.trains.getLast().setLine(line);
                }else {
                    break;
                }
            }
        }

        return lines;
    }


    // Travel by stations
    public void travelByStations(LinkedList<Line> lines, Queue<Driver> driverQueue) {
        System.out.println("---------------------------------------");

        for (Line line : lines) {
            System.out.println("______________");
            System.out.println("Line " + line.getName() + " Number of trains: " + line.trains.size());

            for (Train train : line.trains) {
                new TrainRun(train, driverQueue);
            }
            System.out.println("______________");
        }
        System.out.println("---------------------------------------");
    }

}
