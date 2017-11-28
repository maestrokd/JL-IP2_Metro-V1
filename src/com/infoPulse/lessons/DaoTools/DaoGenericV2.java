package com.infoPulse.lessons.DaoTools;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.stmt.DeleteBuilder;
import com.j256.ormlite.support.ConnectionSource;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.SQLException;
import java.util.Collection;
import java.util.List;

public class DaoGenericV2<T, K> implements DaoObjectV2<T, K> {

    // Fields
    private Class <T> entityClass;
    private Class<K> keyClass;
    ConnectionSource connectionSource;
    Dao<T, K> objectDao;


    // Constructors
    public DaoGenericV2(Class <T> entityClass, Class<K> keyClass) {
        this.connectionSource = ConnectionSql.getInstance().getConnectionSource();
        this.entityClass = entityClass;
        this.keyClass = keyClass;

        try {
            objectDao = DaoManager.createDao(connectionSource, entityClass);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    // Methods
    @Override
    public int nextId() {
        return 0;
    }

    @Override
    public void add(T object) {
        try {
            objectDao.create(object);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void addAll(Collection<T> objects) {
        try {
            objectDao.create(objects);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(T object) {
        try {
            objectDao.update(object);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateAll(Collection<T> objects) {
        for (T object : objects) {
            try {
                objectDao.update(object);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public T getForID(K id) {
        try {
            return objectDao.queryForId(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<T> getAll() {
        List<T> tList = null;
        try {
            return objectDao.queryForAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tList;
    }

    @Override
    public void deleteForID(K id) {

        String nameOfIdColumn = null;

        // TODO in other method
        Field[] fields = entityClass.getDeclaredFields();
        for (Field field : fields) {
            if (field.isAnnotationPresent(DatabaseField.class)) {
                Annotation annotation = field.getAnnotation(DatabaseField.class);
                try {
                    Method method = annotation.annotationType().getMethod("generatedId", Boolean.class);
                    boolean isGeneratedId = false;
                    isGeneratedId = (boolean) method.invoke(annotation);

                    if (isGeneratedId) {
                        nameOfIdColumn = field.getName();
                    }
                } catch (NoSuchMethodException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                }
            }
        }

        DeleteBuilder<T, K> objectDeleteBuilder = objectDao.deleteBuilder();

        try {
            objectDeleteBuilder.where().eq(nameOfIdColumn, id);
            objectDeleteBuilder.delete();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteAll() {
        DeleteBuilder<T, K> objectDeleteBuilder = objectDao.deleteBuilder();
        try {
            objectDeleteBuilder.delete();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
