package com.infoPulse.lessons;

import com.infoPulse.lessons.DatabaseTableClases.Line;
import com.infoPulse.lessons.DatabaseTableClases.Train;
import com.infoPulse.lessons.DatabaseTableClases.Wagon;

import java.util.LinkedList;

public class MetroFactory {


    public static Depot  createWagonsInDepot(int numberOfWagons) {
        Depot depot = new Depot(numberOfWagons);
        return depot;
    }

    // Collect the Trains from wagons in Depot
    public static LinkedList<Train> collectTrains(Depot depot) {
        LinkedList<Train> trains = new LinkedList<>();
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
        return trains;
    }


    // Create Lines and distribution of Trains along the Line
    public static LinkedList<Line> createAndFillLines(LinkedList<Train> trains, String[] namesOfLines) {
        LinkedList<Line> lines = new LinkedList<>();
        int numberOfLines = namesOfLines.length;

        // TODO numberOfStations
        int numberOfStations = 6;

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


}
