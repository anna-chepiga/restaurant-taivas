<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Manage storage</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/style.css">
</head>
<body>
<div id="outer">
    <jsp:include page="../../header.jsp"/>
    <div id="cont">
        <p><a href="${pageContext.request.contextPath}/admin/add-new-ingredient">Add new ingredient</a></p>

        <p>
            Find ingredient by name:

        <form action="${pageContext.request.contextPath}/admin/find-ingredient" method="get" autocomplete="off">
            <input type="text" name="ingredientName" list="ingredientList">
            <datalist id="ingredientList">
                <c:forEach items="${ingredients}" var="ingr">
                    <option>${ingr.name}</option>
                </c:forEach>
            </datalist>
            <input type="submit" value="Find">
        </form>
        </p>

        <table border="1" cellpadding="5">
            <tr>
                <th>Ingredient name</th>
                <th>Amount</th>
                <th>Actions</th>
            </tr>

            <c:forEach items="${ingredients}" var="ingredient">
                <tr>
                    <td><p>${ingredient.name}</p>
                    </td>
                    <td><p>${ingredient.amount}</p>
                    </td>
                    <td>
                        <a href="${pageContext.request.contextPath}/admin/edit-ingredient-name/${ingredient.name}">Edit
                            name</a>,
                        <a href="${pageContext.request.contextPath}/admin/edit-ingredient-amount/${ingredient.name}">Edit
                            amount</a>,
                        <a href="${pageContext.request.contextPath}/admin/delete-ingredient/${ingredient.name}">Delete</a>
                    </td>
                </tr>
            </c:forEach>
        </table>
        <img align="center" width="900px" src="${pageContext.request.contextPath}/resources/images/divider2.png">
        <jsp:include page="../../login-details.jsp"/>
    </div>

    <jsp:include page="../../footer.jsp"/>
</div>
</body>
</html>