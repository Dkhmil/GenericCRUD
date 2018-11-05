package com.khmil.Dao;

import java.util.List;

public interface GenericDao<T, ID> {

    T create(T t);

    T read(ID id);

    T update(T t);

    void delete(ID id);

    List<T> readAll();
}


