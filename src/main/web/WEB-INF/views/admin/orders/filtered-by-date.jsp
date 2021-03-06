<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Filtered by date: ${date}</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/style.css">
</head>
<body>
<div id="outer">
    <jsp:include page="../../header.jsp"/>
    <div id="cont">
        <p>Orders filtered by date: <span id="hdrname">${date}</span> </p>

        <table border="1" cellpadding="5">
            <tr>
                <th>Order ID</th>
                <th>Table number</th>
                <th>Waiter</th>
                <th>List of ordered dishes</th>
            </tr>

            <c:forEach items="${orders}" var="order">
                <tr>
                    <td>${order.id}</td>
                    <td>${order.tableNumber}</td>
                    <td>${order.waiter.firstName} ${order.waiter.lastName}</td>
                    <td>
                        <c:forEach items="${order.dishes}" var="dish">
                            ${dish.dishDescription.dishName}<br>
                        </c:forEach>
                    </td>
                </tr>
            </c:forEach>
        </table>

        <p><a href="${pageContext.request.contextPath}/admin/manage-orders">Manage orders</a></p>
        <img align="center" width="900px" src="${pageContext.request.contextPath}/resources/images/divider2.png">
        <jsp:include page="../../login-details.jsp"/>
    </div>

    <jsp:include page="../../footer.jsp"/>
</div>
</body>
</html>
