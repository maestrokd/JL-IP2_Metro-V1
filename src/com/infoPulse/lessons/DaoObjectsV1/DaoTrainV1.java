package com.infoPulse.lessons.DaoObjectsV1;

import com.infoPulse.lessons.DaoTools.ConnectionSql;
import com.infoPulse.lessons.DatabaseTableClases.Train;
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
public class DaoTrainV1 implements DaoObjectV1<Train> {

    private Dao<Train, Integer> trainIntegerDao;

    // Constructors
    public DaoTrainV1(){
//        ConnectionSource connectionSource = ConnectionSql.getConnectionSource();
        try {
            trainIntegerDao = DaoManager.createDao(ConnectionSql.getInstance().getConnectionSource(), Train.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    @Override
    public int nextId() {
        return 0;
    }

    @Override
    public void add(Train object) {

    }

    @Override
    public void addAll(Collection<Train> objects) {
        try {
            trainIntegerDao.create(objects);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Train object) {

    }

    @Override
    public void updateAll(Collection<Train> objects) {
        for (Train train : objects) {
            try {
                trainIntegerDao.update(train);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public Train getForID(int id) {
        return null;
    }

    @Override
    public List<Train> getAll() {
        try {
            return trainIntegerDao.queryForAll();
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
        DeleteBuilder<Train, Integer> trainIntegerDeleteBuilder = trainIntegerDao.deleteBuilder();
        try {
            trainIntegerDeleteBuilder.delete();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
