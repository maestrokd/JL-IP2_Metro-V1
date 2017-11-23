package com.infoPulse.lessons.DaoTools;

import com.infoPulse.lessons.DatabaseTableClases.*;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.sql.SQLException;

public class DaoObjectsDDL {

    private Dao<Driver, Integer> driverIntegerDao;
    private Dao<Line, Integer> lineIntegerDao;
    private Dao<Passenger, Integer> passengerIntegerDao;
    private Dao<Station, Integer> stationIntegerDao;
    private Dao<StationVisit, Integer> stationVisitIntegerDao;
    private Dao<Train, Integer> trainIntegerDao;
    private Dao<TrainRun, Integer> trainRunIntegerDao;
    private Dao<Wagon, Integer> wagonIntegerDao;



    // Constructors
    public DaoObjectsDDL(){
        ConnectionSource connectionSource = ConnectionSql.getInstance().getConnectionSource();
        try {
            driverIntegerDao = DaoManager.createDao(connectionSource, Driver.class);
            lineIntegerDao = DaoManager.createDao(connectionSource, Line.class);
            passengerIntegerDao = DaoManager.createDao(connectionSource, Passenger.class);
            stationIntegerDao = DaoManager.createDao(connectionSource, Station.class);
            stationVisitIntegerDao = DaoManager.createDao(connectionSource, StationVisit.class);
            trainIntegerDao = DaoManager.createDao(connectionSource, Train.class);
            trainRunIntegerDao = DaoManager.createDao(connectionSource, TrainRun.class);
            wagonIntegerDao = DaoManager.createDao(connectionSource, Wagon.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }



    public void createTableDriver() {
        try {
            TableUtils.createTable(driverIntegerDao);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void dropTableDriver() {
        try {
            TableUtils.dropTable(driverIntegerDao, true);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void createTableLine() {
        try {
            TableUtils.createTable(lineIntegerDao);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void dropTableLine() {
        try {
            TableUtils.dropTable(lineIntegerDao, true);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void createTablePassenger() {
        try {
            TableUtils.createTable(passengerIntegerDao);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void dropTablePassenger() {
        try {
            TableUtils.dropTable(passengerIntegerDao, true);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void createTableStation() {
        try {
            TableUtils.createTable(stationIntegerDao);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void dropTableStation() {
        try {
            TableUtils.dropTable(stationIntegerDao, true);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void createTableStationVisit() {
        try {
            TableUtils.createTable(stationVisitIntegerDao);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void dropTableStationVisit() {
        try {
            TableUtils.dropTable(stationVisitIntegerDao, true);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void createTableTrain() {
        try {
            TableUtils.createTable(trainIntegerDao);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void dropTableTrain() {
        try {
            TableUtils.dropTable(trainIntegerDao, true);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void createTableTrainRun() {
        try {
            TableUtils.createTable(trainRunIntegerDao);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void dropTableTrainRun() {
        try {
            TableUtils.dropTable(trainRunIntegerDao, true);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void createTableWagon() {
        try {
            TableUtils.createTable(wagonIntegerDao);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void dropTableWagon() {
        try {
            TableUtils.dropTable(wagonIntegerDao, true);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}
