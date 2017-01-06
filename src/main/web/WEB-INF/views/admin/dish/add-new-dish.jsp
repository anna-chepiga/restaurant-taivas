<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add new dish</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/style.css">
</head>
<body>
<div id="outer">
    <jsp:include page="../../header.jsp"/>
    <div id="cont">
        <form:form action="${pageContext.request.contextPath}/admin/add-new-dish" method="post" commandName="dishDTO" enctype="multipart/form-data">
            <p>Enter dish name: <form:input path="dishName" type="text"/></p>
            <p>Dish price: <form:input path="price" type="text"/></p>
            <p>Dish weight: <form:input path="weight" type="number"/> </p>
            <p>Select category: <form:select path="category" items="${categories}"/></p>
            <p>Upload photo: <input type="file" name="photo" id="photo"></p>

            <p>Select ingredients which will be included to the dish:</p>

            <c:forEach items="${allIngredients}" var="ingr">
                <p><input type="checkbox" name="ingredient" value="${ingr.name}">${ingr.name}</p>
            </c:forEach>

            <p><input type="submit" value="Add"></p>
        </form:form>
        <img align="center" width="900px" src="${pageContext.request.contextPath}/resources/images/divider2.png">
        <jsp:include page="../../login-details.jsp"/>
    </div>

    <jsp:include page="${pageContext.request.contextPath}/footer.jsp"/>
</div>
</body>
</html>