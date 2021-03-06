package com.infoPulse.lessons.DaoObjectsV1;

import com.infoPulse.lessons.DaoTools.ConnectionSql;
import com.infoPulse.lessons.DatabaseTableClases.Passenger;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.stmt.DeleteBuilder;

import java.sql.SQLException;
import java.util.Collection;
import java.util.List;

/**
 * For Dao Version 1
 * Class for tests. Don't use!!!
 * use DaoClasses implements DaoObjectV2
 */
@Deprecated
public class DaoPassengerV1 implements DaoObjectV1<Passenger> {

    private Dao<Passenger, Integer> passengerIntegerDao;

    // Constructors
    public DaoPassengerV1(){
//        ConnectionSource connectionSource = ConnectionSql.getConnectionSource();
        try {
            passengerIntegerDao = DaoManager.createDao(ConnectionSql.getInstance().getConnectionSource(), Passenger.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int nextId() {
        return 0;
    }

    @Override
    public void add(Passenger object) {

    }

    @Override
    public void addAll(Collection<Passenger> objects) {
        try {
            passengerIntegerDao.create(objects);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Passenger object) {

    }

    @Override
    public void updateAll(Collection<Passenger> objects) {
        for (Passenger passenger : objects) {
            try {
                passengerIntegerDao.update(passenger);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public Passenger getForID(int id) {
        return null;
    }

    @Override
    public List<Passenger> getAll() {
        return null;
    }

    @Override
    public void deleteForID(int id) {

    }

    @Override
    public void deleteAll() {
        DeleteBuilder<Passenger, Integer> passengerIntegerDeleteBuilder = passengerIntegerDao.deleteBuilder();
        try {
            passengerIntegerDeleteBuilder.delete();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
