<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Menu: ${menu.name}</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/style.css">
</head>
<body>
<div id="outer">
    <jsp:include page="../header.jsp"/>
    <div id="cont">
        <p>Selected menu: <span id="hdrname">${menu.name}</span> </p>

        <table>
            <c:forEach items="${dishes}" var="dish">
                <tr>
                    <td><img width="150px" src="${pageContext.request.contextPath}/${dish.photoUrl}"></td>
                    <td>
                        <p><a id="linkname" href="${pageContext.request.contextPath}/dish/${dish.dishName}">${dish.dishName}</a></p>
                        <p>${dish.price} USD</p>
                        <p>${dish.weight} grammes</p>
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