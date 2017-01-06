<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Dishes filtered by category ${category}</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/style.css">
</head>
<body>
<div id="outer">
    <jsp:include page="../header.jsp"/>
    <div id="cont">
        <h1>Dishes filtered by category ${category}</h1>

        <table border="1" width="500px">
            <tr>
                <th>Dish</th>
                <th>Price (in USD)</th>
                <th>Weight</th>
            </tr>

            <c:forEach items="${dishes}" var="dish">
                <tr>
                    <td><a href="${pageContext.request.contextPath}/dish/${dish.dishName}">${dish.dishName}</a></td>
                    <td>${dish.price}</td>
                    <td>${dish.weight}</td>
                </tr>
            </c:forEach>
        </table>
        <img align="center" width="900px" src="${pageContext.request.contextPath}/resources/images/divider2.png">
        <jsp:include page="../login-details.jsp"/>
    </div>

    <jsp:include page="../footer.jsp"/>
</div>
</body>
</html>
