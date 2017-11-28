package com.infoPulse.lessons.DaoObjectsV1;

import java.util.Collection;
import java.util.List;

/**
 * For Dao Version 1
 * Interface for tests. Don't use!!!
 * use Interface DaoObjectV2
 */
@Deprecated
public interface DaoObjectV1<T> {

    public int nextId();

    public  void add(T object);

    public  void addAll(Collection<T> objects);

    public  void update(T object);

    public  void updateAll(Collection<T> objects);

    public  T getForID (int id);

    public  List<T> getAll();

    public void deleteForID(int id);

    public void deleteAll();

}
