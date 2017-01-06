<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add new employee</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/style.css">
</head>
<body>
<div id="outer">
    <jsp:include page="../../header.jsp"/>
    <div id="cont">
        <form:form action="${pageContext.request.contextPath}/admin/add-new-employee" method="post" enctype="multipart/form-data" commandName="employeeDTO" >
            <p>First name: <form:input path="firstName" type="text"/></p>
            <p>Last name: <form:input path="lastName" type="text"/></p>
            <p>Position: <form:select path="position" items="${positions}"/> </p>
            <p>Salary: <form:input path="salary" type="number"/></p>
            <p>Upload photo: <input type="file" name="photo"/></p>
            <p><input type="submit" value="Add"></p>
        </form:form>
        <img align="center" width="900px" src="${pageContext.request.contextPath}/resources/images/divider2.png">
        <jsp:include page="../../login-details.jsp"/>
    </div>

    <jsp:include page="${pageContext.request.contextPath}/footer.jsp"/>
</div>
</body>
</html>