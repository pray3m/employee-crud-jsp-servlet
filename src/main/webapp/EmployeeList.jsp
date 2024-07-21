<%@ page import="java.util.List" %>
<%@ page import="com.codewithprem.Employee" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Employee Management</title>
</head>
<body>
<center>
    <h1>Employee Management</h1>
    <h3>
        <a href="AddEmployee.jsp"> Add New Employee </a>
        &nbsp;&nbsp;&nbsp;
        <a href="list"> List All Employees </a>
    </h3>
</center>

<div align="center">
    <table border="1" cellpadding="5">
        <caption><h2>List of Employees</h2></caption>
        <tr>
            <th>ID</th>
            <th>Name</th>
            <th>Position</th>
            <th>Salary</th>
            <th>Actions</th>
        </tr>
        <%
            List<Employee> employeeList = (List<Employee>) request.getAttribute("employeeList");
            if (employeeList != null) {
                for (Employee employee : employeeList) {
        %>
        <tr>
            <td><%= employee.getId() %>
            </td>
            <td><%= employee.getName() %>
            </td>
            <td><%= employee.getPosition() %>
            </td>
            <td><%= employee.getSalary() %>
            </td>
            <td>
                <a href="editEmployee?id=<%= employee.getId() %>">
                    <button>Edit</button>
                </a>
                <a href="deleteEmployee">
                    <button>Delete</button>
                </a>
            </td>
        </tr>
        <%
            }
        } else {
        %>
        <tr>
            <td colspan="5"> No employees found.</td>
        </tr>
        <%
            }
        %>


    </table>
</div>

</body>
</html>
