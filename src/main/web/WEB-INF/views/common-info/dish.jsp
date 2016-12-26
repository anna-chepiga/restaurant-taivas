<%@page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Dish: ${dish.dishName}</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/style.css">
</head>
<body>
<div id="outer">
    <jsp:include page="../header.jsp"/>
    <div id="cont">
        <table>
            <tr>
                <td><img src="${pageContext.request.contextPath}/${dish.photoUrl}"></td>
                <td>
                    <p><span id="hdrname">${dish.dishName}</span></p>
                    <p>Weight: ${dish.weight} grammes</p>
                    <p>Price: ${dish.price} USD</p>
                    <p>Ingredients:</p>

                    <ul>
                        <c:forEach items="${ingredients}" var="ingredient">
                            <li>${ingredient.name}</li>
                        </c:forEach>
                    </ul>
                </td>
            </tr>
        </table>

        <img align="center" width="900px" src="${pageContext.request.contextPath}/resources/images/divider2.png">
        <jsp:include page="../login-details.jsp"/>

    </div>

    <jsp:include page="/footer"/>
</div>
</body>
</html>