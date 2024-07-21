package com.codewithprem;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;

//@WebServlet(name = "employeeServlet", value = "/employees")
public class EmployeeAPIServlet extends HttpServlet {
    private EmployeeDAO employeeDAO;

    @Override
    public void init() {
        employeeDAO = new EmployeeDAO();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        List<Employee> employees;
        try {
            employees = employeeDAO.getAllEmployees();
            PrintWriter out = resp.getWriter();
            for (Employee employee : employees) {
                out.println("ID: " + employee.getId() + ", Name: " + employee.getName() + ", Position: " + employee.getPosition() + ", Salary: " + employee.getSalary());
            }
            out.flush();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            resp.getWriter().print("Internal server error");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");

        String name = req.getParameter("name");
        String position = req.getParameter("position");
        String salaryStr = req.getParameter("salary");

        if (name.isEmpty() || position.isEmpty() || salaryStr.isEmpty()) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            resp.getWriter().print("All fields are required: name, position, and salary.");
            return;
        }

        try {
            double salary = Double.parseDouble(salaryStr);

            Employee employee = new Employee();
            employee.setName(name);
            employee.setPosition(position);
            employee.setSalary(salary);

            employeeDAO.addEmployee(employee);
            PrintWriter out = resp.getWriter();
            out.print("Employee added successfully");

        } catch (NumberFormatException e) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            resp.getWriter().print("Invalid salary format. Salary must be a number.");
        } catch (SQLException | ClassNotFoundException e) {
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            resp.getWriter().print("Error adding employee");
        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");

        String name = req.getParameter("name");
        String position = req.getParameter("position");
        String salaryStr = req.getParameter("salary");
        int id = Integer.parseInt(req.getParameter("id"));

        if (name.isEmpty() || position.isEmpty() || salaryStr.isEmpty()) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            resp.getWriter().print("All fields are required: name, position, and salary.");
            return;
        }

        try {
            double salary = Double.parseDouble(salaryStr);

            Employee employee = new Employee();
            employee.setId(id);
            employee.setName(name);
            employee.setPosition(position);
            employee.setSalary(salary);

            employeeDAO.updateEmployee(employee);
            PrintWriter out = resp.getWriter();
            out.print("Employee updated successfully");

        } catch (NumberFormatException e) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            resp.getWriter().print("Invalid salary format. Salary must be a number.");
        } catch (SQLException | ClassNotFoundException e) {
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            resp.getWriter().print("Error updating employee");
        }

    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");

        int employeeId = 0;
        employeeId = Integer.parseInt(req.getParameter("id"));

        if (employeeId == 0) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            resp.getWriter().print("All fields are required: name, position, and salary.");
            return;
        }

        try {
            employeeDAO.deleteEmployee(employeeId);
            PrintWriter out = resp.getWriter();
            out.print("Employee deleted successfully");
        } catch (SQLException | ClassNotFoundException e) {
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            resp.getWriter().print("Error deleting employee");
        }
    }
}
