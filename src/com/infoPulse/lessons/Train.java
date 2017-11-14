package com.infoPulse.lessons;

import java.util.LinkedList;

public class Train {

    // Fields
    public static final int maxNumberOfSubwayCarsInTrain = 5;
    private static int count = 1;

    private LinkedList<Wagon> wagons = new LinkedList<>();

    private int train_id;
    private String name;

    private int line_id;
    private Line line;

    private int driver_id;
    private Driver driver;


    // Constructors
    public Train() {
        this.name = "Train_" + count++;

        //TODO setID for new train (Database?)
//        train_id = count-1;
    }


    // Getters and Setters
    public LinkedList<Wagon> getWagons() {
        return wagons;
    }

    public void setWagons(LinkedList<Wagon> wagons) {
        this.wagons = wagons;
    }

    public String getName() {
        return name;
    }

    public int getTrain_id() {
        return train_id;
    }

    public Line getLine() {
        return line;
    }

    public void setLine(Line line) {
        this.line = line;
    }

    public int getDriver_id() {
        return driver_id;
    }

    public void setDriver_id(int driver_id) {
        this.driver_id = driver_id;
    }

    public Driver getDriver() {
        return driver;
    }

    public void setDriver(Driver driver) {
        this.driver = driver;
    }


    // Methods
    public void getInfo (){
        System.out.print(name + " | ");
        for (Wagon wagon : wagons) {
            System.out.print(wagon.getType() + " | ");
        }
    }


    public void addWagon(Wagon wagon, LinkedList<Wagon> subwayTrainReturnToDepot){

        if (wagons.size() == maxNumberOfSubwayCarsInTrain){
            subwayTrainReturnToDepot.addLast(wagon);
            return;
        }
        if (Wagon.firstType.equals(wagon.getType())) {
            if (wagons.size() == 0
                    || !Wagon.firstType.equals(wagons.getFirst().getType())) {
                wagon.setTrain(this);
                wagons.addFirst(wagon);
            } else {
                wagon.setTrain(this);
                wagons.addLast(wagon);
            }
        } else if (Wagon.secondType.equals(wagon.getType())) {

            if (wagons.size() == 0) {
                wagon.setTrain(this);
                wagons.add(0, wagon);
                return;
            }
            if (wagons.size() < 3
                    || (Wagon.firstType.equals(wagons.getFirst().getType())
                    && Wagon.firstType.equals(wagons.getLast().getType()))) {
                wagon.setTrain(this);
                wagons.add(1, wagon);
                return;
            }
            if ((Wagon.firstType.equals(wagons.getFirst().getType())
                    || Wagon.firstType.equals(wagons.getLast().getType()))
                    && wagons.size() < 4) {
                wagon.setTrain(this);
                wagons.add(1, wagon);
                return;
            }

            // Return of wagon to the subwayTrainReturnToDepot
            subwayTrainReturnToDepot.addLast(wagon);
        }
    }
}


