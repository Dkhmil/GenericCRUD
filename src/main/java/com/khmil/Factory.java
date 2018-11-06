package com.khmil;


import com.khmil.Dao.EmployeeDaoImpl;
import com.khmil.model.Employee;

import java.sql.*;

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

    public static void main(String[] args) {
        Employee employee = new Employee(7l, "Іван", "Gtnhjdbx", "Петренко", "інженер", "17000");
        EmployeeDaoImpl employeeDao = new EmployeeDaoImpl(connection, employee.getClass());
        //+  employeeDao.create(employee);
        //+  employeeDao.read(1l);
        //+  employeeDao.update(employee);
        //+  employeeDao.delete(7l);
        //? employeeDao.readAll(); - переписуэ даны в листі, розмір корректно відображає
        // firstName поле не вноситься
    }
}


