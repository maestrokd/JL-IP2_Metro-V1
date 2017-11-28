package com.infoPulse.lessons.DaoTools;

import java.util.Collection;
import java.util.List;

public interface DaoObjectV2<T, K> {

    public int nextId();

    public  void add(T object);

    public  void addAll(Collection<T> objects);

    public  void update(T object);

    public  void updateAll(Collection<T> objects);

    public  T getForID(K id);

    public  List<T> getAll();

    public void deleteForID(K id);

    public void deleteAll();

}
