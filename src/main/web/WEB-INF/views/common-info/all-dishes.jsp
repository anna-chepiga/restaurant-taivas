<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>All dishes</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/style.css">
</head>
<body>
<div id="outer">
    <jsp:include page="../header.jsp"/>
    <div id="cont">
        <table>
            <c:forEach items="${allDishes}" var="dish">
                <tr>
                    <td><img src="${pageContext.request.contextPath}/${dish.photoUrl}"></td>
                    <td>
                        <a id="linkname" href="${pageContext.request.contextPath}/dish/${dish.dishName}">${dish.dishName}</a>
                        <p>${dish.weight} gr.</p>
                        <p>${dish.price} USD</p>
                    </td>
                </tr>
            </c:forEach>
        </table>
        <img align="center" width="900px" src="${pageContext.request.contextPath}/resources/images/divider2.png">
        <jsp:include page="../login-details.jsp"/>
    </div>

    <jsp:include page="${pageContext.request.contextPath}/footer.jsp"/>
</div>
</body>
</html>
