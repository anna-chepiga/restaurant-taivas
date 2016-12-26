<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add dish to menu</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/style.css">
</head>
<body>
<div id="outer">
    <jsp:include page="../../header.jsp"/>
    <div id="cont">
        <p>Add dishes to menu <span id="hdrname">${menuName}</span></p>
        <p>Select dishes to add:</p>

        <form action="${pageContext.request.contextPath}/admin/add-dish-to-menu/${menuName}" method="post">
            <c:forEach items="${dishesToAdd}" var="dish">
                <p><input type="checkbox" name="dish" value="${dish.dishName}">${dish.dishName}</p>
            </c:forEach>
            <p><input type="submit" value="Add"></p>
        </form>
        <img align="center" width="900px" src="${pageContext.request.contextPath}/resources/images/divider2.png">
        <jsp:include page="../../login-details.jsp"/>
    </div>

    <jsp:include page="/footer"/>
</div>
</body>
</html>
