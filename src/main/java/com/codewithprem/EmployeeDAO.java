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
        preparedStatement.executeUpdate();
        conn.close();
    }

    public List<Employee> getAllEmployees() throws SQLException, ClassNotFoundException {
        List<Employee> employeeList = new ArrayList<>();

        Connection conn = DatabaseUtil.getConnection();
        String sql = "SELECT * FROM employee";
        Statement statement = conn.createStatement();
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

    public Employee getEmployeeById(int id) throws SQLException, ClassNotFoundException {
        Connection conn = DatabaseUtil.getConnection();
        String sql = "SELECT * FROM employee WHERE id=?";
        PreparedStatement preparedStatement = conn.prepareStatement(sql);
        preparedStatement.setInt(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();

        Employee employee = null;
        if (resultSet.next()) {
            employee = new Employee();
            employee.setId(resultSet.getInt("id"));
            employee.setName(resultSet.getString("name"));
            employee.setPosition(resultSet.getString("position"));
            employee.setSalary(resultSet.getDouble("salary"));
        }
        conn.close();
        return employee;
    }


    public void updateEmployee(Employee employee) throws SQLException, ClassNotFoundException {
        Connection conn = DatabaseUtil.getConnection();
        String sql = "UPDATE employee SET name=?, position=?, salary=? WHERE id=?";
        PreparedStatement preparedStatement = conn.prepareStatement(sql);
        preparedStatement.setString(1, employee.getName());
        preparedStatement.setString(2, employee.getPosition());
        preparedStatement.setDouble(3, employee.getSalary());
        preparedStatement.setInt(4, employee.getId());
        preparedStatement.executeUpdate();
        conn.close();
    }

    public void deleteEmployee(int employeeId) throws SQLException, ClassNotFoundException {
        Connection conn = DatabaseUtil.getConnection();
        String sql = "DELETE FROM employee WHERE id=?";
        PreparedStatement preparedStatement = conn.prepareStatement(sql);
        preparedStatement.setInt(1, employeeId);
        preparedStatement.executeUpdate();
        conn.close();
    }

}
