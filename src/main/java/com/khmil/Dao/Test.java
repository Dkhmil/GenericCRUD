/*

package com.khmil.Dao;
import com.khmil.annotations.TableName;

import java.lang.reflect.Field;
import java.sql.*;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public abstract class AbstractDao<T, ID> extends ObjectMapper implements GenericDao<T, ID> {
    protected final Connection connection;
    private Class<?> clazz;
    private Map<String, Object> fields = new LinkedHashMap<>();
    private Map<String, Object> types = new LinkedHashMap<>();
    String tableName ;
    StringBuilder colomns = new StringBuilder();
    StringBuilder values = new StringBuilder();
    StringBuilder update = new StringBuilder();


    protected AbstractDao(Connection connection) {
        this.connection = connection;
    }

    protected abstract Object getObjectFromResultSet(ResultSet resultSet) throws SQLException;

    public void dataMapper(T t) throws IllegalAccessException {
        clazz = t.getClass();
        tableName = clazz.getAnnotation(TableName.class).name();
        Field[] localFields = clazz.getDeclaredFields();
        for (Field field : localFields) {
            String colomn = field.getAnnotation(ColomnName.class).name();
            if (colomn != null) {
                Object value;
                Object type;
                field.setAccessible(true);
                value = field.get(t);
                type = field.getType().getSimpleName();
                fields.put(colomn, value);
                types.put(colomn, type);
            }

        }
        for (String key : fields.keySet()) {
            colomns.append(key);
            if (colomns.length() > 1) {
                colomns.append(",");
            }
        }
        colomns = colomns.deleteCharAt(colomns.length() - 1);
        for (Object value : fields.values()) {
            values.append("'" + value + "'");
            if (values.length() > 1) {
                values.append(",");
            }
        }
        values = values.deleteCharAt(values.length() - 1);

        for (Map.Entry<String, Object> entity : fields.entrySet()) {
            update.append(entity.getKey() + " = '");
            update.append(entity.getValue() + "',");
        }
        update = update.deleteCharAt(update.length() - 1);
      //  System.out.println(update);
        System.out.println(colomns);


    }


    @Override
    public void create(T t) {
        try {
            dataMapper(t);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        String Sql = "INSERT INTO " + tableName + "(" + colomns + ") VALUES(" + values + ");";
        PreparedStatement statement;
        try {
            statement = connection.prepareStatement(Sql);
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    @Override
    public T read(ID id) {
        String Sql = "SELECT * FROM EMPLOYEE WHERE ID = " + "'" + id + "';";
        ResultSet resultSet;
        Statement statement;
        Object instance = null;
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(Sql);
            while (resultSet.next()) {
                instance = mapRersultSetToObject(resultSet, Employee.class);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println(instance);
        return (T) instance;
    }

    @Override
    public T update(T t) {
        try {
            dataMapper(t);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        String Sql = "UPDATE " + tableName + " SET " + update +
                " WHERE ID = " + fields.get("ID");
//        System.out.println(Sql);
        Statement statement;
        ResultSet resultSet;
        Object instance = null;
        try {
            statement = connection.createStatement();
            statement.executeUpdate(Sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println(instance);
        return (T) instance;
    }

    @Override
    public void delete(T t, ID id) {
        try {
            dataMapper(t);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        String Sql = "DELETE FROM " + tableName + " WHERE ID = " + "'" + fields.get("ID") + "';";
        //System.out.println(Sql);
        PreparedStatement statement;
        try {
            statement = connection.prepareStatement(Sql);
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();

        }
    }

    @Override
    public List<T> readAll(T t) {
        try {
            dataMapper(t);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        String Sql = "SELECT " + colomns + " FROM " + tableName + ";";
        //System.out.println(Sql);
        Statement statement;
        ResultSet resultSet;
        List<T> list = new ArrayList<>();
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(Sql);
            while (resultSet.next()) {
                list.add((T) getObjectFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        //System.out.println(list.get(2));
        return list;
    }
}

*/
