<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Edit employee: id ${employee.id}</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/style.css">
</head>
<body>
<div id="outer">
    <jsp:include page="../../header.jsp"/>
    <div id="cont">
        <p>Edit employee: <span id="hdrname">id ${employee.id}, ${employee.firstName} ${employee.lastName}</span></p>

        <form action="${pageContext.request.contextPath}/admin/edit-employee/${employee.id}" method="post">
            <p>New first name: <input type="text" placeholder="${employee.firstName}" value="${employee.firstName}" name="firstName"></p>
            <p>New last name: <input type="text" placeholder="${employee.lastName}" value="${employee.lastName}" name="lastName"></p>
            <p>New salary: <input type="number" placeholder="${employee.salary}" value="${employee.salary}" name="salary"></p>
            <p>New position: <select name="position">
                <c:forEach items="${positions}" var="position">
                    <option value="${position}">${position}</option>
                </c:forEach>
            </select></p>

            <input type="submit" value="Edit">
        </form>
        <img align="center" width="900px" src="${pageContext.request.contextPath}/resources/images/divider2.png">
        <jsp:include page="../../login-details.jsp"/>
    </div>

    <jsp:include page="/footer"/>
</div>
</body>
</html>
