<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Edit dish ${dish.dishName}</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/style.css">
</head>
<body>
<div id="outer">
    <jsp:include page="../../header.jsp"/>
    <div id="cont">
        <h1>Edit dish ${dish.dishName}</h1>

        <form action="${pageContext.request.contextPath}/admin/edit-dish/${dish.id}" method="post">

            <p>New name: <input type="text" placeholder="${dish.dishName}" value="${dish.dishName}" name="name"></p>
            <p>New weight: <input type="number" placeholder="${dish.weight}" value="${dish.weight}" name="weight"></p>
            <p>New price: <input type="text" placeholder="${dish.price}" value="${dish.price}" name="price"></p>
            <p>New category: <select name="category">
                <c:forEach items="${categories}" var="category">
                    <option value="${category}">${category}</option>
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
