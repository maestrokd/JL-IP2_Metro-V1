package com.infoPulse.lessons.DatabaseTableClases;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.util.Date;
import java.util.Random;

@DatabaseTable(tableName = "stationVisit")
public class StationVisit {

    @DatabaseField(generatedId = true, useGetSet = true)
    private int stationVisit_id;

    @DatabaseField(useGetSet = true)
    private int trainRun_id;

    @DatabaseField(useGetSet = true)
    private int station_id;

    @DatabaseField(useGetSet = true)
    private Date visitDate;

    @DatabaseField(useGetSet = true)
    private int passengersIn;

    @DatabaseField(useGetSet = true)
    private int passengersOut;

    private TrainRun trainRun;
    private Station station;

    @DatabaseField(useGetSet = true)
    private String station_name;

    private String visitInfo = "";


    // Constructors
    public StationVisit() {}

    public StationVisit(TrainRun trainRun, Station station) {

        this.trainRun = trainRun;
        this.station = station;

//        this.trainRun_id = trainRun.getTrainRun_id();
        this.station_id = station.getStation_id();

        // TODO now date
        this.visitDate = new Date();
        this.station_name = station.getName();

        if (trainRun.getLine().getStations().getLast().equals(station)) {

            // If station is last
            lastStationVisit(trainRun.getTrain(), station);
        } else {

            // If station not last
            stationVisit(trainRun.getTrain(), station);
        }

        station.getStationVisits().add(this);
    }


    // Getters and Setters


    public int getStationVisit_id() {
        return stationVisit_id;
    }

    public void setStationVisit_id(int stationVisit_id) {
        this.stationVisit_id = stationVisit_id;
    }

    public int getTrainRun_id() {
        return trainRun_id;
    }

    public void setTrainRun_id(int trainRun_id) {
        this.trainRun_id = trainRun_id;
    }

    public int getStation_id() {
        return station_id;
    }

    public void setStation_id(int station_id) {
        this.station_id = station_id;
    }

    public Date getVisitDate() {
        return visitDate;
    }

    public void setVisitDate(Date visitDate) {
        this.visitDate = visitDate;
    }

    public int getPassengersIn() {
        return passengersIn;
    }

    public void setPassengersIn(int passengersIn) {
        this.passengersIn = passengersIn;
    }

    public int getPassengersOut() {
        return passengersOut;
    }

    public void setPassengersOut(int passengersOut) {
        this.passengersOut = passengersOut;
    }

    public TrainRun getTrainRun() {
        return trainRun;
    }

    public Station getStation() {
        return station;
    }

    public String getStation_name() {
        return station_name;
    }

    public void setStation_name(String station_name) {
        this.station_name = station_name;
    }

    public String getVisitInfo() {
        return visitInfo;
    }

    // Methods
    // If station not last
    public void stationVisit(Train train, Station station) {

        int countPassenger;
        int numberOfPassengersToOut;
        Random random = new Random();
//        String visitInfo = "";



//        System.out.println();
//        System.out.println(station.getInfo() + " | " + station.getPassengers().size() + " passengers are ready to go");
        visitInfo += "\n" + station.getInfo() + " | " + station.getPassengers().size() + " passengers are ready to go\n";

        for (Wagon wagon : train.getWagons()) {
//            System.out.print(wagon.getName() + " | " + wagon.getPassengers().size() + " | ");
            visitInfo += wagon.getName() + " | " + wagon.getPassengers().size() + " | ";


            // Moving the passengers from the Wagon
            if (wagon.getPassengers().size() != 0) {
                numberOfPassengersToOut = random.nextInt(wagon.getPassengers().size());
                passengersOut += numberOfPassengersToOut;

//                wagon.passengers.removeAll(wagon.passengers.subList(0, numberOfPassengersToOut));
//                countPassenger = 0;
                for (int i = 0; i < numberOfPassengersToOut; i++) {
                    wagon.getPassengers().remove();
//                    countPassenger++;
                }

//                System.out.print(numberOfPassengersToOut + " -->> out | ");
                visitInfo += numberOfPassengersToOut + " -->> out | ";
            } else {
//                System.out.print("0 -->> out | ");
                visitInfo += "0 -->> out | ";
            }


            // Moving the passengers to the Wagon
            countPassenger = 0;
            while ((wagon.getPassengers().size() < Wagon.maxSize) && (!station.getPassengers().isEmpty())) {
                wagon.getPassengers().add(station.getPassengers().pollFirst());
                countPassenger++;
            }
            passengersIn += countPassenger;
//            System.out.print("in <<-- " + countPassenger + " | ");
            visitInfo += "in <<-- " + countPassenger + " | ";
//            System.out.println(wagon.getName() + " | " + wagon.getPassengers().size());
            visitInfo += wagon.getName() + " | " + wagon.getPassengers().size() + "\n";
        }
//        System.out.println(station.getInfo() + " | " + station.getPassengers().size() + " passengers left at the station");
        visitInfo += station.getInfo() + " | " + station.getPassengers().size() + " passengers left at the station";

        System.out.println(visitInfo);
    }


    // If station is last
    public void lastStationVisit(Train train, Station station) {

        int numberOfPassengersToOut;
//        String visitInfo = "";

//        System.out.println();
//        System.out.println(station.getInfo() + " | Last station! |" + station.getPassengers().size() + " passengers who finished the trip");
        visitInfo += "\n" + station.getInfo() + " | Last station! |" + station.getPassengers().size() + " passengers who finished the trip\n";

        for (Wagon wagon : train.getWagons()) {
//            System.out.print(wagon.getName() + " | " + wagon.getPassengers().size() + " | ");
            visitInfo += wagon.getName() + " | " + wagon.getPassengers().size() + " | ";


            // Moving the passengers from the Wagon
            if (wagon.getPassengers().size() != 0) {
                numberOfPassengersToOut = wagon.getPassengers().size();
                passengersOut += numberOfPassengersToOut;
                station.getPassengers().addAll(wagon.getPassengers());
                wagon.getPassengers().clear();
//                System.out.print(numberOfPassengersToOut + " -->> out | ");
                visitInfo += numberOfPassengersToOut + " -->> out | ";
            } else {
//                System.out.print("0 -->> out | ");
                visitInfo += "0 -->> out | ";
            }
//            System.out.println(wagon.getName() + " | " + wagon.getPassengers().size());
            visitInfo += wagon.getName() + " | " + wagon.getPassengers().size() + "\n";
        }
//        System.out.println(station.getInfo() + " | " + station.getPassengers().size() + " passengers who finished the trip");
        visitInfo += station.getInfo() + " | " + station.getPassengers().size() + " passengers who finished the trip\n";

        System.out.println(visitInfo);
    }

}
