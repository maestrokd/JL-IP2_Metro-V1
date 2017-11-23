package com.infoPulse.lessons.DaoObjects;

import com.infoPulse.lessons.DaoTools.ConnectionSql;
import com.infoPulse.lessons.DaoTools.DaoObject;
import com.infoPulse.lessons.DatabaseTableClases.Driver;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.stmt.DeleteBuilder;
import com.j256.ormlite.support.ConnectionSource;

import java.sql.SQLException;
import java.util.Collection;
import java.util.List;

public class DaoDriver implements DaoObject<Driver> {

    private Dao<Driver, Integer> driverIntegerDao;
//    private QueryBuilder<Driver, Integer> driverIntegerQueryBuilder;


    public DaoDriver(){
//        ConnectionSource connectionSource = ConnectionSql.getConnectionSource();
        try {
            driverIntegerDao = DaoManager.createDao(ConnectionSql.getInstance().getConnectionSource(), Driver.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }
//        driverIntegerQueryBuilder = driverIntegerDao.queryBuilder();
    }


    @Override
    public int nextId() {
        return 0;
    }

    @Override
    public void add(Driver object) {
        try {
            driverIntegerDao.create(object);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void addAll(Collection<Driver> objects) {
        try {
            driverIntegerDao.create(objects);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Driver object) {
        try {
            driverIntegerDao.update(object);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    // TODO without loop
    @Override
    public void updateAll(Collection<Driver> objects) {

        for (Driver driver : objects) {
            try {
                driverIntegerDao.update(driver);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public Driver getForID(int id) {
        try {
            return driverIntegerDao.queryForId(id);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Driver> getAll() {
        try {
            return driverIntegerDao.queryForAll();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void deleteForID(int id) {
        DeleteBuilder<Driver, Integer> driverIntegerDeleteBuilder = driverIntegerDao.deleteBuilder();

//        driverIntegerDeleteBuilder.where().isNull("name");
//        driverIntegerDeleteBuilder.delete();
//        driverIntegerDeleteBuilder.where().eq("experience", 0.0);
//        driverIntegerDeleteBuilder.delete();

        try {
            driverIntegerDeleteBuilder.where().eq("driver_id", id);
            driverIntegerDeleteBuilder.delete();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteAll() {
        DeleteBuilder<Driver, Integer> driverIntegerDeleteBuilder = driverIntegerDao.deleteBuilder();
        try {
            driverIntegerDeleteBuilder.delete();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
