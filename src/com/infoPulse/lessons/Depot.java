package com.infoPulse.lessons;

import java.util.LinkedList;
import java.util.Random;

public class Depot {

    // Fields
    private LinkedList<Wagon> wagons = new LinkedList<>();
    private LinkedList<Wagon> wagonsReturnToDepot = new LinkedList<>();

    private Random random = new Random();


    // Constructor
    public Depot (int numberOfWagonsInDepot){
        System.out.println("---------------------------------------");

          for (int i = 0; i < numberOfWagonsInDepot; i++){
              wagons.add(new Wagon(random.nextInt(100)));
          }
         System.out.println("Number of wagons collected in the depot: " + wagons.size());
        System.out.println("---------------------------------------");
    }


    // Getters and Setters
    public LinkedList<Wagon> getWagons() {
        return wagons;
    }

    public void setWagons(LinkedList<Wagon> wagons) {
        this.wagons = wagons;
    }

    public LinkedList<Wagon> getWagonsReturnToDepot() {
        return wagonsReturnToDepot;
    }

    public void setWagonsReturnToDepot(LinkedList<Wagon> wagonsReturnToDepot) {
        this.wagonsReturnToDepot = wagonsReturnToDepot;
    }

}
