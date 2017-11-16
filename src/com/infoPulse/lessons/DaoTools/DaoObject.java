package com.infoPulse.lessons.DaoTools;

import java.util.Collection;
import java.util.List;

public interface DaoObject <T> {

    public void createTable();

    public void dropTable();

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
