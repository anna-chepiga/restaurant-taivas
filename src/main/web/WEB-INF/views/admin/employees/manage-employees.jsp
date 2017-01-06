<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Manage employees</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/style.css">
</head>
<body>
<div id="outer">
    <jsp:include page="../../header.jsp"/>
    <div id="cont">
        <p><a href="${pageContext.request.contextPath}/admin/add-new-employee">Add new employee</a></p>

        <table border="1" cellpadding="5">
            <tr>
                <th>Photo</th>
                <th width="100px">First name</th>
                <th width="120px">Last name</th>
                <th width="120px">Position</th>
                <th width="90px">Salary</th>
                <th width="90px">Actions</th>
            </tr>

            <c:forEach items="${allEmployees}" var="employee">
                <tr>
                    <td>
                        <img src="${pageContext.request.contextPath}/${employee.photoUrl}" width="100px">

                    </td>
                    <td> ${employee.firstName}</td>
                    <td> ${employee.lastName}</td>
                    <td> ${employee.position.name}</td>
                    <td> ${employee.salary}</td>
                    <td><p><a href="${pageContext.request.contextPath}/admin/edit-employee/${employee.id}">Edit info</a></p>
                        <p><a href="${pageContext.request.contextPath}/admin/edit-employee-photo/${employee.id}">Edit photo</a>
                </tr>
            </c:forEach>
        </table>
        <img align="center" width="900px" src="${pageContext.request.contextPath}/resources/images/divider2.png">
        <jsp:include page="../../login-details.jsp"/>
    </div>

    <jsp:include page="../../footer.jsp"/>
</div>
</body>
</html>