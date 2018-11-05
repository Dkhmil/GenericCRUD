import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public class EmployeeMapper extends ObjectMapper<Employee> {
    @Override
    public Employee mapRersultSetToObject(ResultSet resultSet, Class clazz) throws SQLException {
        return super.mapRersultSetToObject(resultSet, clazz);
    }
}
