<%@page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Employee ${employee.firstName}</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/style.css">
</head>
<body>
<div id="outer">
    <jsp:include page="../header.jsp"/>
    <div id="cont">
        <table>
            <tr>
                <td><img src="${pageContext.request.contextPath}/${employee.photoUrl}"></td>
                <td>
                    <p style="font-size: x-large">${employee.firstName} ${employee.lastName}</p>
                    <p>${employee.position.name}</p>
                </td>
            </tr>
        </table>
        <img align="center" width="900px" src="${pageContext.request.contextPath}/resources/images/divider2.png">
        <jsp:include page="../login-details.jsp"/>
    </div>

    <jsp:include page="/footer"/>
</div>
</body>

<jsp:include page="../login-details.jsp"/>
</html>
