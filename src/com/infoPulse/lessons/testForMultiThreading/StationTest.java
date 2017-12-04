package com.infoPulse.lessons.testForMultiThreading;

import java.util.LinkedList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class StationTest {

    // Fields

    private String name;

    private LinkedList<PassengerTest> passengersAtHall = new LinkedList<>();

    private LinkedList<PassengerTest> passengers = new LinkedList<>();

    // For Run with Lock and Condition
    Lock lock =  new ReentrantLock();
    Condition condition = lock.newCondition();


    // Getters and Setters


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LinkedList<PassengerTest> getPassengersAtHall() {
        return passengersAtHall;
    }

    public void setPassengersAtHall(LinkedList<PassengerTest> passengersAtHall) {
        this.passengersAtHall = passengersAtHall;
    }

    public synchronized LinkedList<PassengerTest> getPassengers() {
        return passengers;
    }

    public void setPassengers(LinkedList<PassengerTest> passengers) {
        this.passengers = passengers;
    }

    public Lock getLock() {
        return lock;
    }

    public void setLock(Lock lock) {
        this.lock = lock;
    }

    public Condition getCondition() {
        return condition;
    }

    public void setCondition(Condition condition) {
        this.condition = condition;
    }
}
