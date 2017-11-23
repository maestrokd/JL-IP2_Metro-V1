package com.infoPulse.lessons.DaoObjects;

import com.infoPulse.lessons.DaoTools.ConnectionSql;
import com.infoPulse.lessons.DaoTools.DaoObject;
import com.infoPulse.lessons.DatabaseTableClases.Wagon;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.stmt.DeleteBuilder;
import com.j256.ormlite.support.ConnectionSource;

import java.sql.SQLException;
import java.util.Collection;
import java.util.List;

public class DaoWagon implements DaoObject<Wagon> {
    private Dao<Wagon, Integer> wagonIntegerDao;
//    private QueryBuilder<Wagon, Integer> wagonIntegerQueryBuilder;


    public DaoWagon(){
//        ConnectionSource connectionSource = ConnectionSql.getConnectionSource();
        try {
            wagonIntegerDao = DaoManager.createDao(ConnectionSql.getInstance().getConnectionSource(), Wagon.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }
//        wagonIntegerQueryBuilder = wagonIntegerDao.queryBuilder();
    }


    @Override
    public int nextId() {
        return 0;
    }

    @Override
    public void add(Wagon object) {
        try {
            wagonIntegerDao.create(object);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void addAll(Collection<Wagon> objects) {
        try {
            wagonIntegerDao.create(objects);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Wagon object) {
        try {
            wagonIntegerDao.update(object);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    // TODO without loop
    @Override
    public void updateAll(Collection<Wagon> objects) {
        for (Wagon wagon : objects) {
            try {
                wagonIntegerDao.update(wagon);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public Wagon getForID(int id) {
        try {
            return wagonIntegerDao.queryForId(id);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Wagon> getAll() {
        try {
            return wagonIntegerDao.queryForAll();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void deleteForID(int id) {
        DeleteBuilder<Wagon, Integer> wagonIntegerDeleteBuilder = wagonIntegerDao.deleteBuilder();
        try {
            wagonIntegerDeleteBuilder.where().eq("wagon_id", id);
            wagonIntegerDeleteBuilder.delete();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteAll() {
        DeleteBuilder<Wagon, Integer> wagonIntegerDeleteBuilder = wagonIntegerDao.deleteBuilder();
        try {
            wagonIntegerDeleteBuilder.delete();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
