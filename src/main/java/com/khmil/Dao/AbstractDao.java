package com.khmil.Dao;

import java.sql.Connection;
import java.util.List;
import java.util.Map;

import static com.khmil.Dao.QueryGenerator.queryBuilder;

public abstract class AbstractDao<T, ID> implements GenericDao<T, ID> {

    protected final Connection connection;
    private Class<?> clazz;
    private Map<String, String> queries;

    public AbstractDao(Connection connection, Class<?> clazz) {
        this.connection = connection;
        this.clazz = clazz;
    }

    @Override
    public T create(T t) {
        queries = queryBuilder(clazz);
        String sql = queries.get("create");
        System.out.println(sql);
        return null;
    }

    @Override
    public T read(ID id) {
        queries = queryBuilder(clazz);
        String sql = queries.get("read");
        System.out.println(sql);
        return null;
    }

    @Override
    public T update(T t) {
        queries = queryBuilder(clazz);
        String sql = queries.get("update");
        System.out.println(sql);
        return null;
    }

    @Override
    public void delete(ID id) {
        queries = queryBuilder(clazz);
        String sql = queries.get("delete");
        System.out.println(sql);
    }

    @Override
    public List<T> readAll() {
        queries = queryBuilder(clazz);
        String sql = queries.get("findAll");
        System.out.println(sql);
        return null;
    }
}
