package com.infoPulse.lessons.testForMultiThreading;

public class CreatorPassengersTest implements Runnable {

    // Fields
    private StationTest stationTest;


    public CreatorPassengersTest(StationTest stationTest) {
        this.stationTest = stationTest;
    }

    // Run with synchronized
    @Override
    public void run() {
        PassengerTest passengerTest;
        while (true) {

            synchronized (stationTest.getPassengersAtHall()) {
                passengerTest = new PassengerTest();
                stationTest.getPassengersAtHall().add(passengerTest);
                System.out.println(passengerTest.getName() + " came in to hall.");

                stationTest.getPassengersAtHall().notifyAll();
            }
            try {
                Thread.sleep(300);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


    // Run with Lock and Condition
//    @Override
//    public void run() {
//        PassengerTest passengerTest;
//
//        while (true) {
//            stationTest.getLock().lock();
//            try {
//                passengerTest = new PassengerTest();
//                stationTest.getPassengersAtHall().add(passengerTest);
//                System.out.println(passengerTest.getName() + " came in to hall.");
//
//                stationTest.getCondition().signalAll();
//            } finally {
//                stationTest.getLock().unlock();
//            }
//            try {
//                Thread.sleep(200);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }
//    }


}
