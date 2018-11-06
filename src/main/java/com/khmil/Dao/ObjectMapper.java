package com.khmil.Dao;

import com.khmil.Factory;
import com.khmil.annotations.ColumnName;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.sql.*;
import java.util.*;

public class ObjectMapper<T> {

    public static T mapResultSetToObject(ResultSet resultSet, Class clazz) throws SQLException {
        Map<String, Object> fromRs = new LinkedHashMap<>();
        ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
        Map<String, Field> fields = new HashMap<>();
        if (resultSet.next()) {
            for (int i = 1; i <= resultSetMetaData.getColumnCount(); i++) {
                fromRs.put(resultSetMetaData.getColumnName(i), resultSet.getObject(i));
            }
        }
        List<Field> fieldList = Arrays.asList(clazz.getDeclaredFields());
        for (Field field : fieldList) {
            ColumnName col = field.getAnnotation(ColumnName.class);
            if (col != null) {
                field.setAccessible(true);
                fields.put(col.name(), field);
            }
        }
        try {
            T instance = (T) clazz.getConstructor().newInstance();
            for (Map.Entry<String, Object> entity : fromRs.entrySet()) {
                if (entity.getValue() == null) {
                    continue;
                }
                String column = entity.getKey();
                Field field = fields.get(column);
                if (field != null) {
                    field.set(instance, convertInstanceOfObject(entity.getValue()));
                }
            }
            return instance;

        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return null;
    }

    private T convertInstanceOfObject(Object o) {
        try {
            return (T) o;
        } catch (ClassCastException e) {
            return null;
        }
    }
}

