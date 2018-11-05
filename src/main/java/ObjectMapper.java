import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.sql.*;
import java.util.*;
import javax.persistence.Column;

public class ObjectMapper<T> {

    public T mapRersultSetToObject(ResultSet resultSet, Class clazz) throws SQLException {
        Map<String, Object> fromRs = new LinkedHashMap<>();
        ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
        Map<String, Field> fields = new HashMap<>();
        while (resultSet.next()) {
            for (int i = 1; i <= resultSetMetaData.getColumnCount(); i++) {
                fromRs.put(resultSetMetaData.getColumnName(i), resultSet.getObject(i));
            }
        }
        List<Field> fieldList = Arrays.asList(clazz.getDeclaredFields());
        for (Field field : fieldList) {
            ColomnName col = field.getAnnotation(ColomnName.class);
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


    public List<T> findAll(Class clazz) throws SQLException {
        Connection connection = Factory.connection;
        List<T> list = new ArrayList<>();
        String query = "SELECT * FROM EMPLOYEE where id = ?; ";
        PreparedStatement statement = connection.prepareStatement(query);
        ResultSet resultSet;

        for (int i = 1; i <= 5; i++) {
            statement.setObject(1,i + "");
            resultSet = statement.executeQuery();
            T t = mapRersultSetToObject(resultSet, clazz);
            list.add(t);

        }
        System.out.println(list);
        return list;


    }
    private T convertInstanceOfObject(Object o) {
        try {
            return (T) o;
        } catch (ClassCastException e) {
            return null;
        }
    }
}


