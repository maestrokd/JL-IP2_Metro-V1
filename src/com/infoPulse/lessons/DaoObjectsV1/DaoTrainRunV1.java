package com.infoPulse.lessons.DaoObjectsV1;

import com.infoPulse.lessons.DaoTools.ConnectionSql;
import com.infoPulse.lessons.DatabaseTableClases.TrainRun;
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
public class DaoTrainRunV1 implements DaoObjectV1<TrainRun> {

    private Dao<TrainRun, Integer> trainRunIntegerDao;
//    private QueryBuilder<Driver, Integer> driverIntegerQueryBuilder;


    // Constructors
    public DaoTrainRunV1(){
//        ConnectionSource connectionSource = ConnectionSql.getConnectionSource();
        try {
            trainRunIntegerDao = DaoManager.createDao(ConnectionSql.getInstance().getConnectionSource(), TrainRun.class);
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
    public void add(TrainRun object) {

    }

    @Override
    public void addAll(Collection<TrainRun> objects) {
        try {
            trainRunIntegerDao.create(objects);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(TrainRun object) {

    }

    @Override
    public void updateAll(Collection<TrainRun> objects) {
        for (TrainRun trainRun : objects) {
            try {
                trainRunIntegerDao.update(trainRun);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public TrainRun getForID(int id) {
        return null;
    }

    @Override
    public List<TrainRun> getAll() {
        return null;
    }

    @Override
    public void deleteForID(int id) {

    }

    @Override
    public void deleteAll() {
        DeleteBuilder<TrainRun, Integer> trainRunIntegerDeleteBuilder = trainRunIntegerDao.deleteBuilder();
        try {
            trainRunIntegerDeleteBuilder.delete();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
