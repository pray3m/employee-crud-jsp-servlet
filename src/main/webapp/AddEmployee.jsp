<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Add New Employee</title>
</head>
<body>
<center>
    <h1>Add New Employee</h1>
    <form action="addEmployee" method="post">
        <table>
            <tr>
                <td>Name:</td>
                <td><input type="text" name="name" required/></td>
            </tr>
            <tr>
                <td>Position:</td>
                <td><input type="text" name="position" required/></td>
            </tr>
            <tr>
                <td>Salary:</td>
                <td><input type="number" name="salary" step="0.01" required/></td>
            </tr>
            <tr>
                <td colspan="2"><input type="submit" value="Add Employee"/></td>
            </tr>
        </table>
    </form>
</center>
</body>
</html>
