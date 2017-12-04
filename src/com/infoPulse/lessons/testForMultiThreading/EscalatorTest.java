package com.infoPulse.lessons.testForMultiThreading;

import java.util.LinkedList;

public class EscalatorTest implements Runnable{

    // Fields
    private String name;
    private StationTest stationTest;

    private LinkedList<PassengerTest> passengers = new LinkedList<>();

    public EscalatorTest(String name, StationTest stationTest) {

        this.name = name;
        this.stationTest = stationTest;
    }

    // Run with synchronized
    @Override
    public void run() {

        PassengerTest passengerTest;
        while (true) {
            synchronized (stationTest.getPassengersAtHall()) {
                if (stationTest.getPassengersAtHall().isEmpty()) {
                    try {
                        stationTest.getPassengersAtHall().wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    continue;
                }

                passengerTest = stationTest.getPassengersAtHall().poll();
                passengers.add(passengerTest);
                System.out.println(passengerTest.getName() + " came in to " + this.name);

                passengerTest = passengers.pollFirst();
                stationTest.getPassengers().add(passengerTest);
                System.out.println(passengerTest.getName() + " came out from " + this.name);

            }

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }


    // Run with Lock and Condition
//    @Override
//    public void run() {
//
//        PassengerTest passengerTest;
//
//        while (true) {
//            stationTest.getLock().lock();
//            try {
//                if (stationTest.getPassengersAtHall().isEmpty()) {
//                    try {
//                        stationTest.getCondition().await();
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                    continue;
//                }
//
//                passengerTest = stationTest.getPassengersAtHall().poll();
//                stationTest.getPassengers().add(passengerTest);
//                System.out.println(passengerTest.getName() + " came in to " + this.name);
//
//            } finally {
//                stationTest.getLock().unlock();
//            }
//
//            try {
//                Thread.sleep(1000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//
//        }
//    }


}
