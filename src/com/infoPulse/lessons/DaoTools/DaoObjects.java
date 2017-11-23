package com.infoPulse.lessons.DaoTools;

import com.infoPulse.lessons.DatabaseTableClases.Driver;
import com.infoPulse.lessons.DatabaseTableClases.Train;
import com.infoPulse.lessons.DatabaseTableClases.Wagon;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.stmt.DeleteBuilder;
import com.j256.ormlite.stmt.QueryBuilder;
import com.j256.ormlite.support.ConnectionSource;

import java.sql.SQLException;
import java.util.List;
import java.util.Queue;


/**
 * Class for tests. Don't use!!!
 * use DaoClasses implements DaoObject
 */
@Deprecated
public class DaoObjects {

    static private Dao<Driver, Integer> driverIntegerDao;
    static private QueryBuilder<Driver, Integer> driverIntegerQueryBuilder;
    static private Dao<Train, Integer> trainIntegerDao;
    static private QueryBuilder<Train, Integer> trainIntegerQueryBuilder;
    static private Dao<Wagon, Integer> wagonIntegerDao;
    static private QueryBuilder<Wagon, Integer> wagonIntegerQueryBuilder;


//    public DaoObjects(ConnectionSource connectionSource) {
    static {
        ConnectionSource connectionSource = ConnectionSql.getInstance().getConnectionSource();
        try {
            driverIntegerDao = DaoManager.createDao(connectionSource, Driver.class);
            trainIntegerDao = DaoManager.createDao(connectionSource, Train.class);
            wagonIntegerDao = DaoManager.createDao(connectionSource, Wagon.class);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        driverIntegerQueryBuilder = driverIntegerDao.queryBuilder();
        trainIntegerQueryBuilder = trainIntegerDao.queryBuilder();
        wagonIntegerQueryBuilder = wagonIntegerDao.queryBuilder();
    }

//    public Dao<Driver, Integer> getDriverIntegerDao() {
//        return driverIntegerDao;
//    }
//
//    public QueryBuilder<Driver, Integer> getDriverIntegerQueryBuilder() {
//        return driverIntegerQueryBuilder;
//    }
//
//    public void setDriverIntegerQueryBuilder(QueryBuilder<Driver, Integer> driverIntegerQueryBuilder) {
//        this.driverIntegerQueryBuilder = driverIntegerQueryBuilder;
//    }

    // Methods for Driver
    public static int nextIdDriver() throws SQLException {
        int i = 1;
        while (driverIntegerDao.idExists(i)) {
            i++;
        }
        return i;
    }

    public static void addDriver(Driver driver) throws SQLException {
        driverIntegerDao.create(driver);
    }

    public static void addAllDriver(Queue<Driver> driverQueue) throws SQLException {
        driverIntegerDao.create(driverQueue);
    }

    public static void updateDriver(Driver driver) throws SQLException {
        driverIntegerDao.update(driver);
    }

    public static Driver getDriverForID (int id) throws SQLException {
        return driverIntegerDao.queryForId(id);
    }

    public static List<Driver> getDriverAll() throws SQLException {
        return driverIntegerDao.queryForAll();
    }

    public static void methodDriver() throws SQLException {
        DeleteBuilder<Driver, Integer> driverIntegerDeleteBuilder = driverIntegerDao.deleteBuilder();
//        driverIntegerDeleteBuilder.where().isNull("name");
//        driverIntegerDeleteBuilder.delete();
        driverIntegerDeleteBuilder.where().eq("experience", 0.0);
        driverIntegerDeleteBuilder.delete();
//        driverIntegerQueryBuilder.where().eq("experience", 0);
//        driverIntegerQueryBuilder.
    }


    // Methods for Train
    public static int nextIdTrain() throws SQLException {

        int i = 1;
        while (trainIntegerDao.idExists(i)) {
            i++;
        }
        return i;
    }

    public static void addTrain(Train train) throws SQLException {
        trainIntegerDao.create(train);
        trainIntegerDao.refresh(train);
    }

    public static void updateTrain(Train train) throws SQLException {
        trainIntegerDao.update(train);
    }

    public static Train getTrainForID (int id) throws SQLException {
        return trainIntegerDao.queryForId(id);
    }

    public static List<Train> getTrainAll() throws SQLException {
        return trainIntegerDao.queryForAll();
    }


    // Methods for Wagons
    public static int nextIdWagon() throws SQLException {

        int i = 1;
        while (wagonIntegerDao.idExists(i)) {
            i++;
        }
        return i;
    }

    public static void addWagon(Wagon wagon) throws SQLException {
        wagonIntegerDao.create(wagon);
    }

    public static void updateWagon(Wagon wagon) throws SQLException {
        wagonIntegerDao.update(wagon);
    }

    public static Wagon getWagonForID (int id) throws SQLException {
        return wagonIntegerDao.queryForId(id);
    }

    public static List<Wagon> getWagonAll() throws SQLException {
        return wagonIntegerDao.queryForAll();
    }

}
