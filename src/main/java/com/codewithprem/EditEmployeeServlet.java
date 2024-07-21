package com.codewithprem;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

public class EditEmployeeServlet extends HttpServlet {
    private EmployeeDAO employeeDAO;

    @Override
    public void init() throws ServletException {
        employeeDAO = new EmployeeDAO();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));

        try {
            Employee employee = employeeDAO.getEmployeeById(id);
            req.setAttribute("employee", employee);
            req.getRequestDispatcher("EditEmployee.jsp").forward(req, resp);
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        String name = req.getParameter("name");
        String position = req.getParameter("position");
        double salary = Double.parseDouble(req.getParameter("salary"));
        Employee employee = new Employee();
        employee.setId(id);
        employee.setName(name);
        employee.setPosition(position);
        employee.setSalary(salary);

        try {
            employeeDAO.updateEmployee(employee);
            resp.sendRedirect("list");
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
