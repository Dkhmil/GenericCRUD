package com.khmil.Dao;

import com.khmil.model.Employee;
import java.sql.Connection;
import java.util.List;

public class EmployeeDaoImpl extends AbstractDao<Employee, Long> implements GenericDao<Employee, Long> {

    public EmployeeDaoImpl(Connection connection, Class<?> clazz) {
        super(connection, clazz);
    }

    @Override
    public Employee create(Employee employee) {
        return super.create(employee);
    }

    @Override
    public Employee read(Long aLong) {
        return super.read(aLong);
    }

    @Override
    public Employee update(Employee employee) {
        return super.update(employee);
    }

    @Override
    public void delete(Long aLong) {
        super.delete(aLong);
    }

    @Override
    public List<Employee> readAll() {
        return super.readAll();
    }
}
