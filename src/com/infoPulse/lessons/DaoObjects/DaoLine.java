package com.infoPulse.lessons.DaoObjects;

import com.infoPulse.lessons.DaoTools.ConnectionSql;
import com.infoPulse.lessons.DaoTools.DaoObject;
import com.infoPulse.lessons.DatabaseTableClases.Line;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.stmt.DeleteBuilder;
import com.j256.ormlite.support.ConnectionSource;

import java.sql.SQLException;
import java.util.Collection;
import java.util.List;

public class DaoLine implements DaoObject<Line> {

    private Dao<Line, Integer> lineIntegerDao;

    // Constructors
    public DaoLine(){
//        ConnectionSource connectionSource = ConnectionSql.getConnectionSource();
        try {
            lineIntegerDao = DaoManager.createDao(ConnectionSql.getInstance().getConnectionSource(), Line.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    @Override
    public int nextId() {
        return 0;
    }

    @Override
    public void add(Line object) {

    }

    @Override
    public void addAll(Collection<Line> objects) {
        try {
            lineIntegerDao.create(objects);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Line object) {

    }

    @Override
    public void updateAll(Collection<Line> objects) {
        for (Line line : objects) {
            try {
                lineIntegerDao.update(line);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public Line getForID(int id) {
        return null;
    }

    @Override
    public List<Line> getAll() {
        try {
            return lineIntegerDao.queryForAll();
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
        DeleteBuilder<Line, Integer> lineIntegerDeleteBuilder = lineIntegerDao.deleteBuilder();
        try {
            lineIntegerDeleteBuilder.delete();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
