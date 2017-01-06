<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Admin: manage menus</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/style.css">
</head>
<body>
<div id="outer">
    <jsp:include page="../../header.jsp"/>
    <div id="cont">
        <p><a href="${pageContext.request.contextPath}/admin/add-new-menu">Add new menu</a></p>

        <table border="1">
            <tr>
                <th>Menu name</th>
                <th>List of dishes</th>
                <th>Actions</th>
            </tr>

            <c:forEach items="${allMenus}" var="menu">
                <tr>
                    <td><p>${menu.name}</p></td>
                    <td>
                        <c:forEach items="${menu.dishes}" var="dish">
                            ${dish.dishName} <a id="linkremove"
                                                href="${pageContext.request.contextPath}/admin/remove-dish-from-menu/${menu.name}/${dish.dishName}">Remove</a><br>
                        </c:forEach>
                    </td>
                    <td>
                        <p><a href="${pageContext.request.contextPath}/admin/edit-menu-name/${menu.name}">Edit menu name</a></p>
                        <p><a href="${pageContext.request.contextPath}/admin/delete-menu/${menu.name}">Delete menu</a></p>
                        <p><a href="${pageContext.request.contextPath}/admin/add-dish-to-menu/${menu.name}">Add new dish</a></p>
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
