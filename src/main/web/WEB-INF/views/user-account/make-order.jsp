<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Make new order</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/style.css">
</head>
<body>
<div id="outer">
    <jsp:include page="../header.jsp"/>
    <div id="cont">
        <p>user = ${cookie.username.value}</p>

        <h1>Make an order</h1>

        <p>You can order from the following menu: ${currentMenu.name}:</p>

        <c:forEach items="${currentMenu.dishes}" var="dish">
            <ul>
                <li>${dish.dishName} <form action="${pageContext.request.contextPath}/add-to-cart/${dish.dishName}" method="get"><input type="submit" value="Add to cart"></form></li>
            </ul>
        </c:forEach>

        <form action="${pageContext.request.contextPath}/process-order" method="post"><input type="submit" value="Process order"></form>

        <%--<c:choose>
            <c:when test="${cart.size() == 0}">
                <p>Your cart is empty</p>
            </c:when>
            <c:otherwise>
                <p>Cart:</p>
                <c:forEach items="${cart}" var="dishname">
                    <ul>
                        <li>${dishname}</li>
                    </ul>
                </c:forEach>
            </c:otherwise>
        </c:choose>--%>
        <img align="center" width="900px" src="${pageContext.request.contextPath}/resources/images/divider2.png">
        <jsp:include page="../login-details.jsp"/>
    </div>

    <jsp:include page="../footer.jsp"/>
</div>
</body>
</html>
