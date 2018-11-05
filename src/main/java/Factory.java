import java.lang.reflect.InvocationTargetException;
import java.sql.*;
import java.util.List;
import java.util.Map;

public class Factory {

    static Connection connection = null;

    static {
        try {
            Class.forName("org.h2.Driver");
            connection = DriverManager.getConnection("jdbc:h2:tcp://localhost/~/test2", "sa", "");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() {
        return connection;
    }

    public static void main(String[] args) throws IllegalAccessException, SQLException, NoSuchMethodException, InstantiationException, InvocationTargetException {
        EmployeeDaoImpl d = new EmployeeDaoImpl(getConnection());
          Employee emp = new Employee(5l, "John", "Fitzgerald" , "Kennedy", "president", "4257600$");
        //d.delete(emp,1l);
        //d.readAll(emp);
       // d.read(emp,3l);
        // d.create(emp4);
        // d.update(emp);
        //
       EmployeeMapper employeeMapper = new EmployeeMapper();
       String query = "SELECT * FROM EMPLOYEE where id = 3; ";
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(query);
        Employee employee = employeeMapper.mapRersultSetToObject(resultSet, Employee.class);
        System.out.println(employeeMapper.mapRersultSetToObject(resultSet,Employee.class));

    }

}


