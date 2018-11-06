package com.khmil.Dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import static com.khmil.Dao.ObjectMapper.mapResultSetToObject;
import static com.khmil.Dao.QueryGenerator.queryBuilder;

public abstract class AbstractDao<T, ID> implements GenericDao<T, ID> {

    protected final Connection connection;
    private Class<?> clazz;
    private Map<String, String> queries;
    PreparedStatement statement;
    ResultSet resultSet;
    Object instance;

    public AbstractDao(Connection connection, Class<?> clazz) {
        this.connection = connection;
        this.clazz = clazz;
    }

    @Override
    public T create(T t) {
        queries = queryBuilder(clazz);
        String sql = queries.get("create");
        try {
            statement = connection.prepareStatement(sql);
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return t;
    }

    @Override
    public T read(ID id) {
        queries = queryBuilder(clazz);
        String sql = queries.get("read");
        try {
            statement = connection.prepareStatement(sql);
            statement.setObject(1, id);
            resultSet = statement.executeQuery();
            if(resultSet.next()) {
                instance = mapResultSetToObject(resultSet, clazz);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return (T) instance;
    }

    @Override
    public T update(T t) {
        queries = queryBuilder(clazz);
        String sql = queries.get("update");
        try {
            statement = connection.prepareStatement(sql);
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return t;
    }

    @Override
    public void delete(ID id) {
        queries = queryBuilder(clazz);
        String sql = queries.get("delete");
        try {
            statement = connection.prepareStatement(sql);
            statement.setObject(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<T> readAll() {
        List<T> list = new ArrayList<>();
        queries = queryBuilder(clazz);
        String sql = queries.get("findAll");
        try {
            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                instance = mapResultSetToObject(resultSet, clazz);
                list.add((T) instance);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
}
