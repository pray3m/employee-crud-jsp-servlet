package com.codewithprem;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

public class AddEmployeeServlet extends HttpServlet {
    private EmployeeDAO employeeDAO;

    @Override
    public void init() {
        employeeDAO = new EmployeeDAO();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String position = req.getParameter("position");
        double salary = Double.parseDouble(req.getParameter("salary"));

        Employee newEmployee = new Employee();
        newEmployee.setName(name);
        newEmployee.setPosition(position);
        newEmployee.setSalary(salary);

        try {
            employeeDAO.addEmployee(newEmployee);
            resp.sendRedirect("list"); 
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
