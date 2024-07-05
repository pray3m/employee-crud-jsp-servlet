package com.codewithprem;

import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;

//@WebServlet(name = "employeeServlet", value = "/employees")
public class EmployeeServlet extends HttpServlet {
    private EmployeeDAO employeeDAO;
    private Gson gson;

    @Override
    public void init() {
        employeeDAO = new EmployeeDAO();
        gson = new Gson();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");
        List<Employee> employees = null;
        try {
            employees = employeeDAO.getAllEmployees();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        
        String employeeJsonString =  gson.toJson(employees);
        PrintWriter out = resp.getWriter();
        out.print(employeeJsonString);
        out.flush();
    }
}
