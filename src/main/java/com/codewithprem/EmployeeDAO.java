package com.codewithprem;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDAO {
    public void addEmployee(Employee employee) throws ClassNotFoundException, SQLException {
        Connection conn = DatabaseUtil.getConnection();
        String sql = "INSERT INTO employee (name, position, salary) VALUES (?, ?, ?)";
        PreparedStatement preparedStatement = conn.prepareStatement(sql);
        preparedStatement.setString(1, employee.getName());
        preparedStatement.setString(2, employee.getPosition());
        preparedStatement.setDouble(3, employee.getSalary());
        conn.close();
    }

    public List<Employee> getAllEmployees() throws SQLException, ClassNotFoundException {
        List<Employee> employeeList = new ArrayList<>();

        Connection conn = DatabaseUtil.getConnection();
        String sql = "SELECT * FROM employee";
        java.sql.Statement statement = conn.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);

        while (resultSet.next()) {
            Employee employee = new Employee();
            employee.setId(resultSet.getInt("id"));
            employee.setName(resultSet.getString("name"));
            employee.setPosition(resultSet.getString("position"));
            employee.setSalary(resultSet.getDouble("salary"));
            employeeList.add(employee);
        }
        conn.close();
        return employeeList;
    }
}
