import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class EmployeeDaoImpl extends AbstractDao<Employee,Long> implements GenericDao<Employee,Long> {

    protected EmployeeDaoImpl(Connection connection) {
        super(connection);
    }

    protected Employee getObjectFromResultSet(ResultSet resultSet) throws SQLException {
        return new Employee(
                (Long) resultSet.getObject("ID"),
                (String) resultSet.getObject("FIRST_NAME"),
                (String) resultSet.getObject("MIDDLE_NAME"),
                (String) resultSet.getObject("LAST_NAME"),
                (String) resultSet.getObject("TITLE"),
                (String) resultSet.getObject("SALARY"));
    }

    @Override
    public void dataMapper(Employee employee) throws IllegalAccessException {
        super.dataMapper(employee);
    }

    @Override
    public void create(Employee employee) {
        super.create(employee);
    }
    @Override
    public Employee read(Employee emp,Long id) {
        return super.read(emp,id);
    }
    @Override
    public Employee update(Employee employee) {
        return super.update(employee);
    }
    @Override
    public void delete(Employee employee,Long id) {
        super.delete(employee,id);
    }
    @Override
    public List<Employee> readAll(Employee employee) {
        return super.readAll(employee);
    }
}
