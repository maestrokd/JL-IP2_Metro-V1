package com.infoPulse.lessons.DaoObjectsV1;

import com.infoPulse.lessons.DaoTools.ConnectionSql;
import com.infoPulse.lessons.DatabaseTableClases.Station;
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
public class DaoStationV1 implements DaoObjectV1<Station> {

    private Dao<Station, Integer> stationIntegerDao;

    // Constructors
    public DaoStationV1(){
//        ConnectionSource connectionSource = ConnectionSql.getConnectionSource();
        try {
            stationIntegerDao = DaoManager.createDao(ConnectionSql.getInstance().getConnectionSource(), Station.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    @Override
    public int nextId() {
        return 0;
    }

    @Override
    public void add(Station object) {

    }

    @Override
    public void addAll(Collection<Station> objects) {
        try {
            stationIntegerDao.create(objects);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Station object) {

    }

    @Override
    public void updateAll(Collection<Station> objects) {
        for (Station station : objects) {
            try {
                stationIntegerDao.update(station);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public Station getForID(int id) {
        return null;
    }

    @Override
    public List<Station> getAll() {
        try {
            return stationIntegerDao.queryForAll();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void deleteForID(int id) {

    }

    @Override
    public void deleteAll() {
        DeleteBuilder<Station, Integer> stationIntegerDeleteBuilder = stationIntegerDao.deleteBuilder();
        try {
            stationIntegerDeleteBuilder.delete();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
