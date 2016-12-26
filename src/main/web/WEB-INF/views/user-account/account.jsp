<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>User Cabinet</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/style.css">
</head>

<body>
<div id="outer">
    <jsp:include page="../header.jsp"/>
    <div id="cont">
        <h1>User account</h1>

        <h2>Welcome ${cookie.username.value}!</h2>

        <p><b>Your details:</b></p>
        <p>
            Username: ${user.username}<br>
            Name: ${user.name} <a style="font-size: small" href="${pageContext.request.contextPath}/account/edit-name/${user.username}">Edit</a><br>
            Email: ${user.email} <a style="font-size: small" href="${pageContext.request.contextPath}/account/edit-email/${user.username}">Edit</a>
        </p>

        <c:choose>
            <c:when test="${user.orders.size() == 0}">
                <p>You do not have any orders yet. <a href="${pageContext.request.contextPath}/make-order">Make your first order!</a></p>
            </c:when>
            <c:otherwise>
                <p><b>Your orders:</b></p>

                <table border="1">
                    <tr>
                        <td>Order date</td>
                        <td>List of ordered dishes</td>
                    </tr>

                    <c:forEach items="${user.orders}" var="order">
                        <tr>
                            <td>${order.orderDate}</td>
                            <td>
                                <c:forEach items="${order.dishes}" var="dish">
                                    ${dish.dishDescription.dishName}<br>
                                </c:forEach>
                            </td>
                        </tr>
                    </c:forEach>
                </table>

                <p>Make new order</p>

            </c:otherwise>
        </c:choose>
        <img align="center" width="900px" src="${pageContext.request.contextPath}/resources/images/divider2.png">
        <jsp:include page="../login-details.jsp"/>
    </div>

    <jsp:include page="/footer"/>
</div>
</body>

</html>