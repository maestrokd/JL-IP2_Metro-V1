package com.infoPulse.lessons;

import com.infoPulse.lessons.DaoObjectsV1.DaoLineV1;
import com.infoPulse.lessons.DaoObjectsV1.DaoStationV1;
import com.infoPulse.lessons.DaoObjectsV1.DaoObjectV1;
import com.infoPulse.lessons.DatabaseTableClases.*;

import java.util.*;

public class MetroWithDatabase {

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

                    for (Wagon wagon : trains.getLast().getWagons()) {
                        wagon.setTrain_id(trains.getLast().getTrain_id());
                    }

                    break;
                }
            }
        }
        System.out.println("---------------------------------------");
    }



    // Collect the Trains in Depot from wagons in Depot with database
    public void collectTrainsWithDatabase(Depot depot, LinkedList<Train> trains) {
        System.out.println("---------------------------------------");
        System.out.println("Number of wagons in the depot before the formation of trains: " + depot.getWagons().size());

        System.out.println(trains.size());

        for (Train train : trains) {
            while (true) {
//                System.out.println("1");

                if (depot.getWagons().size() == 0) {
//                    System.out.println("2");
                    break;
                }

                train.addWagon(depot.getWagons().removeFirst(), depot.getWagonsReturnToDepot());
                if (train.getWagons().size() == Train.maxNumberOfSubwayCarsInTrain) {

//                    System.out.println("3");

                    for (Wagon wagon : train.getWagons()) {

                        wagon.setTrain_id(train.getTrain_id());
                    }
                    break;
                }
            }
        }

//
//        while (true) {
//            if (depot.getWagons().size() < Train.maxNumberOfSubwayCarsInTrain) {
//                depot.getWagons().addAll(depot.getWagonsReturnToDepot());
//                break;
//            }
//            trains.addLast(new Train());
//
//            while (true) {
//
//                if (depot.getWagons().size() == 0) {
//                    try {
//                        throw new DepotEmptyException();
//                    } catch (DepotEmptyException e) {
////                       System.out.println("Error! The wagons of the required type in the depot are over!");
//
//
//                        // Return of wagons of an unfinished train to the wagonsReturnToDepot
//                        if (trains.getLast().getWagons().size() != Train.maxNumberOfSubwayCarsInTrain) {
//                            for (Wagon wagon : trains.getLast().getWagons()) {
//                                depot.getWagons().addLast(wagon);
//                            }
//                            trains.removeLast();
//                        }
//                        break;
//                    }
//                }
//
//                trains.getLast().addWagon(depot.getWagons().removeFirst(), depot.getWagonsReturnToDepot());
//
//                if (trains.getLast().getWagons().size() == Train.maxNumberOfSubwayCarsInTrain) {
//
//                    for (Wagon wagon : trains.getLast().getWagons()) {
//                        wagon.setTrain_id(trains.getLast().getTrain_id());
//                    }
//
//                    break;
//                }
//            }
//        }
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
                    line.getTrains().add(trains.pollFirst());
                    line.getTrains().getLast().setLine(line);
                }else {
                    break;
                }
            }
        }

        return lines;
    }


    // Create Lines and distribution of Trains along the Line With Database
    public LinkedList<Line> createAndFillLinesWithDatabase(LinkedList<Train> trains, String[] namesOfLines) {
        LinkedList<Line> lines = new LinkedList<>();
        LinkedList<Station> stations = new LinkedList<>();
        DaoObjectV1 daoObjectV1 = new DaoLineV1();
        lines.addAll(daoObjectV1.getAll());
        daoObjectV1 = new DaoStationV1();
        stations.addAll(daoObjectV1.getAll());

        int numberOfLines = namesOfLines.length;

        // TODO numberOfStations
        int numberOfStations = 3;

        for (Line line : lines) {
            for (int i = 0; i < numberOfStations; i++) {
                line.getStations().add(stations.poll());
                line.getStations().getLast().setLine(line);
            }
        }



        while (!trains.isEmpty()) {
            for (Line line :lines) {
                if (!trains.isEmpty()) {
                    line.getTrains().add(trains.pollFirst());
                    line.getTrains().getLast().setLine(line);
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
            System.out.println("Line " + line.getName() + " Number of trains: " + line.getTrains().size());

            for (Train train : line.getTrains()) {
                new TrainRun(train, driverQueue);
            }
            System.out.println("______________");
        }
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
