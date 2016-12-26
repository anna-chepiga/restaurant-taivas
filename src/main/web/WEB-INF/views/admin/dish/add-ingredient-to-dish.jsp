<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add ingredient to dish</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/style.css">
</head>
<body>
<div id="outer">
    <jsp:include page="../../header.jsp"/>
    <div id="cont">
        <p>Add ingredients to dish <span id="hdrname">${dishName}</span></p>
        <p>Select ingredients to add:</p>

        <form action="${pageContext.request.contextPath}/admin/add-ingredient-to-dish/${dishName}" method="post">
            <c:forEach items="${ingredientsToAdd}" var="ingr">
                <p><input type="checkbox" name="ingredient" value="${ingr.name}">${ingr.name}</p>
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