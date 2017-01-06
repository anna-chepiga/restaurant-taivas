<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Employees</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/style.css">
</head>
<body>
<div id="outer">
    <jsp:include page="../header.jsp"/>
    <div id="cont">
        <h1>Our employees</h1>

        <table>
            <c:forEach items="${employees}" var="employee">
                <tr>
                    <td><img width="150px" src="${pageContext.request.contextPath}/${employee.photoUrl}"></td>
                    <td>
                        <p><a id="linkname" href="${pageContext.request.contextPath}/employee/${employee.firstName}">${employee.firstName}</a></p>
                        <p>${employee.position.name}</p>
                    </td>
                </tr>
            </c:forEach>
        </table>

        <img align="center" width="900px" src="${pageContext.request.contextPath}/resources/images/divider2.png">
        <jsp:include page="../login-details.jsp"/>
    </div>

    <jsp:include page="../footer.jsp"/>
</div>
</body>
</html>