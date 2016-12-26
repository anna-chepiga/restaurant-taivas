<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Manage dishes</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/style.css">
</head>
<body>
<div id="outer">
    <jsp:include page="../../header.jsp"/>
    <div id="cont">
        <p><a href="${pageContext.request.contextPath}/admin/add-new-dish">Add new dish</a></p>

        <table border="1">
            <tr>
                <th>Dish photo</th>
                <th><p>Dish name</p></th>
                <th>Dish Category</th>
                <th style="width: 100px">Dish weight (in grammes)</th>
                <th style="width: 90px">Dish price (in USD)</th>
                <th>List of ingredients</th>
                <th>Actions</th>
            </tr>

            <c:forEach items="${allDishes}" var="dish">
                <tr>
                    <td>
                        <img src="${pageContext.request.contextPath}/${dish.photoUrl}" width="100px">
                        <p><a href="${pageContext.request.contextPath}/admin/edit-dish-photo/${dish.dishName}">Edit photo</a>
                        </p>
                    </td>

                    <td>${dish.dishName} </td>
                    <td>${dish.category.name}</td>
                    <td>${dish.weight}</td>
                    <td>${dish.price}</td>

                    <td>
                        <c:forEach items="${dish.ingredients}" var="ingredient">
                            ${ingredient.name} <a id="linkremove"
                                href="${pageContext.request.contextPath}/admin/remove-ingredient-from-dish/${dish.dishName}/${ingredient.name}">Remove</a><br>
                        </c:forEach>
                    </td>

                    <td>
                        <p><a href="${pageContext.request.contextPath}/admin/edit-dish/${dish.id}">Edit details</a></p>
                        <p><a href="${pageContext.request.contextPath}/admin/delete-dish/${dish.dishName}">Delete</a></p>
                        <p><a href="${pageContext.request.contextPath}/admin/add-ingredient-to-dish/${dish.dishName}">Add new
                            ingredient</a></p>
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