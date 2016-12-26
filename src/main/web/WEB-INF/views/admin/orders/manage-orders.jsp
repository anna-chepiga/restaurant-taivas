<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Manage orders</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/style.css">
</head>
<body>
<div id="outer">
    <jsp:include page="../../header.jsp"/>
    <div id="cont">
        <form action="${pageContext.request.contextPath}/admin/filter-by-date" method="get">
            <p>Filter by date: <input type="date" name="date"> <input type="submit" value="Filter"></p>
        </form>

        <form action="${pageContext.request.contextPath}/admin/filter-by-waiter" method="get">
            <p>Filter by employee:
                <select name="waiterId">
                    <c:forEach items="${waiters}" var="waiter">
                        <option value="${waiter.id}">${waiter.firstName} ${waiter.lastName}</option>
                    </c:forEach>
                </select>
                <input type="submit" value="Filter"></p>
        </form>

        <form action="${pageContext.request.contextPath}/admin/filter-by-table" method="get">
            <p>Filter by table number:
                <select name="tableNumber">
                    <c:forEach items="${tableNumbers}" var="number">
                        <option value="${number}">${number}</option>
                    </c:forEach>
                </select>
                <input type="submit" value="Filter">
            </p>
        </form>

        <table border="1" cellpadding="5">
            <tr>
                <th>Order date</th>
                <th>Order ID</th>
                <th>Table number</th>
                <th>Waiter</th>
                <th>List of ordered dishes</th>
            </tr>

            <c:forEach items="${allOrders}" var="order">
                <tr>
                    <td>${order.orderDate}</td>
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
        <img align="center" width="900px" src="${pageContext.request.contextPath}/resources/images/divider2.png">
        <jsp:include page="../../login-details.jsp"/>
    </div>

    <jsp:include page="/footer"/>
</div>
</body>
</html>
