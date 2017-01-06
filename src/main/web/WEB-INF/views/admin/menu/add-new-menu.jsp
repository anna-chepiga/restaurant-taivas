<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add new menu</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/style.css">
</head>
<body>
<div id="outer">
    <jsp:include page="../../header.jsp"/>
    <div id="cont">
        <form:form action="${pageContext.request.contextPath}/admin/add-new-menu" method="post" commandName="menuDTO">
            <p>Enter menu name: <form:input path="menuName" type="text" name="menuName"/></p>
            <p>Select dishes which will be included to the menu:</p>

            <c:forEach items="${allDishes}" var="dish">
                <p><input type="checkbox" name="dish" value="${dish.dishName}">${dish.dishName}</p>
            </c:forEach>

            <p><input type="submit" value="Add"></p>
        </form:form>
        <img align="center" width="900px" src="${pageContext.request.contextPath}/resources/images/divider2.png">
        <jsp:include page="../../login-details.jsp"/>
    </div>

    <jsp:include page="../../footer.jsp"/>
</div>
</body>
</html>
