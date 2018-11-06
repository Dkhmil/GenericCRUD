package com.khmil.Dao;


import com.khmil.annotations.ColumnName;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.sql.*;
import java.util.*;

public class ObjectMapper {

    public static Object mapResultSetToObject(ResultSet resultSet, Class clazz) throws SQLException {
        Map<String, Object> fromRs = new LinkedHashMap<>();
        ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
        Map<String, Field> fields = new HashMap<>();
        Object instance;
        for (int i = 1; i <= resultSetMetaData.getColumnCount(); i++) {
            fromRs.put(resultSetMetaData.getColumnName(i), resultSet.getObject(i));
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
            instance = clazz.getConstructor().newInstance();
            for (Map.Entry<String, Object> entity : fromRs.entrySet()) {
                if (entity.getValue() == null) {
                    continue;
                }
                String column = entity.getKey();
                Field field = fields.get(column);
                if (field != null) {
                    field.set(instance, entity.getValue());
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
}


