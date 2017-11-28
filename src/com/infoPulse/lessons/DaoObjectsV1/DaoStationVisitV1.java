package com.infoPulse.lessons.DaoObjectsV1;

import com.infoPulse.lessons.DaoTools.ConnectionSql;
import com.infoPulse.lessons.DatabaseTableClases.StationVisit;
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
public class DaoStationVisitV1 implements DaoObjectV1<StationVisit> {

    private Dao<StationVisit, Integer> stationVisitIntegerDao;
//    private QueryBuilder<Driver, Integer> driverIntegerQueryBuilder;


    // Constructors
    public DaoStationVisitV1(){
//        ConnectionSource connectionSource = ConnectionSql.getConnectionSource();
        try {
            stationVisitIntegerDao = DaoManager.createDao(ConnectionSql.getInstance().getConnectionSource(), StationVisit.class);
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
    public void add(StationVisit object) {

    }

    @Override
    public void addAll(Collection<StationVisit> objects) {
        try {
            stationVisitIntegerDao.create(objects);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(StationVisit object) {

    }

    @Override
    public void updateAll(Collection<StationVisit> objects) {
        for (StationVisit stationVisit : objects) {
            try {
                stationVisitIntegerDao.update(stationVisit);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public StationVisit getForID(int id) {
        return null;
    }

    @Override
    public List<StationVisit> getAll() {
        return null;
    }

    @Override
    public void deleteForID(int id) {

    }

    @Override
    public void deleteAll() {
        DeleteBuilder<StationVisit, Integer> stationVisitIntegerDeleteBuilder = stationVisitIntegerDao.deleteBuilder();
        try {
            stationVisitIntegerDeleteBuilder.delete();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
