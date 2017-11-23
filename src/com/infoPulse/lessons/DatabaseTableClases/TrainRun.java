package com.infoPulse.lessons.DatabaseTableClases;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.util.Date;
import java.util.List;
import java.util.Queue;

@DatabaseTable(tableName = "trainRun")
public class TrainRun {

    @DatabaseField(generatedId = true, useGetSet = true)
    private int trainRun_id;

    @DatabaseField(useGetSet = true)
    private int driver_id;

    @DatabaseField(useGetSet = true)
    private int line_id;

    @DatabaseField(useGetSet = true)
    private int train_id;

    @DatabaseField(useGetSet = true)
    private Date startDate;

    private Driver driver;

    @DatabaseField(useGetSet = true)
    private String driver_name;

    private Line line;

    @DatabaseField(useGetSet = true)
    private String line_name;

    private Train train;

    @DatabaseField(useGetSet = true)
    private String train_name;


    // Constructors
    public TrainRun() {}

    public TrainRun(Train train, Queue<Driver> driverQueue) {

        // Moving the driver to the train
        train.setDriver(driverQueue.poll());
        System.out.println(train.getDriver().getName() + "|" + train.getDriver().getExperience());

        this.driver = train.getDriver();
        this.line = train.getLine();
        this.train = train;

        this.driver_id = this.driver.getDriver_id();
        this.line_id = this.line.getLine_id();
        this.train_id = this.train.getTrain_id();

        this.driver_name = this.driver.getName();
        this.line_name = this.line.getName();
        this.train_name = this.train.getName();

        // TODO now date
        this.startDate = new Date();

        line.getTrainRuns().add(this);
        System.out.println();
        System.out.print(this.getTrain().getName() + " - " + this.getStartDate() + "|");

        trainRun();

        // Get driving experience and Moving the driver from the Train
        train.getDriver().addExperience();
        System.out.println(train.getDriver().getName() + "|" + train.getDriver().getExperience());
        driverQueue.add(train.getDriver());
        train.setDriver(null);
    }


    // Getters and Setters
    public int getTrainRun_id() {
        return trainRun_id;
    }

    public void setTrainRun_id(int trainRun_id) {
        this.trainRun_id = trainRun_id;
    }

    public int getDriver_id() {
        return driver_id;
    }

    public void setDriver_id(int driver_id) {
        this.driver_id = driver_id;
    }

    public int getLine_id() {
        return line_id;
    }

    public void setLine_id(int line_id) {
        this.line_id = line_id;
    }

    public int getTrain_id() {
        return train_id;
    }

    public void setTrain_id(int train_id) {
        this.train_id = train_id;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Driver getDriver() {
        return driver;
    }

    public String getDriver_name() {
        return driver_name;
    }

    public void setDriver_name(String driver_name) {
        this.driver_name = driver_name;
    }

    public Line getLine() {
        return line;
    }

    public String getLine_name() {
        return line_name;
    }

    public void setLine_name(String line_name) {
        this.line_name = line_name;
    }

    public Train getTrain() {
        return train;
    }

    public String getTrain_name() {
        return train_name;
    }

    public void setTrain_name(String train_name) {
        this.train_name = train_name;
    }

    // Methods
    public void trainRun() {
//        System.out.println();
        System.out.println(train.getName() + " rode along the " + line.getName() + " Line");

        for (Station station : line.getStations()) {
            new StationVisit(this, station);
        }

    }

}
