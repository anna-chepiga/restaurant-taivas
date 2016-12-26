<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<div id="lgdetails">
    <c:set var="admin" value="admin"/>

    <c:choose>
        <c:when test="${cookie.username.value == null}">
            <a href="${pageContext.request.contextPath}/login">Login</a>
        </c:when>

        <c:when test="${cookie.username.value == admin}">
            You are logged in as <span id="usrnm">${cookie.username.value}</span>.
            <a href="${pageContext.request.contextPath}/admin-start-page">Go to admin start page</a> or
            <a href="${pageContext.request.contextPath}/logout">Logout</a>
        </c:when>

        <c:otherwise>
            You are logged in as <span id="usrnm">${cookie.username.value}</span>.
            <a href="${pageContext.request.contextPath}/account/${cookie.username.value}">Go to your account</a> or <a
                href="${pageContext.request.contextPath}/logout">Logout</a>
        </c:otherwise>
    </c:choose>
</div>
</body>
</html>
