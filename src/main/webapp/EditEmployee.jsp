<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.codewithprem.Employee" %>
<%
    // Retrieve the employee object from the request
    Employee employee = (Employee) request.getAttribute("employee");
%>
<!DOCTYPE html>
<html>
<head>
    <title>Edit Employee</title>
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

<center>
    <h1>Edit Employee</h1>
    <form action="editEmployee" method="post">
        <input type="hidden" name="id" value="<%= employee.getId() %>">
        <table>
            <tr>
                <td>Name:</td>
                <td><input type="text" name="name" value="<%= employee.getName() %>" required></td>
            </tr>
            <tr>
                <td>Position:</td>
                <td><input type="text" name="position" value="<%= employee.getPosition() %>" required></td>
            </tr>
            <tr>
                <td>Salary:</td>
                <td><input type="number" name="salary" value="<%= employee.getSalary() %>" required></td>
            </tr>
            <tr>
                <td colspan="2" align="center">
                    <input type="submit" value="Update Employee">
                </td>
            </tr>
        </table>
    </form>
</center>
</body>
</html>
