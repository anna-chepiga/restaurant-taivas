<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Error</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/style.css">
</head>
<body>

<div id="outer">
    <jsp:include page="header.jsp"/>
    <div id="cont">
        <h1>Error Page</h1>

        <h3 style="color: #c12e2a">${exception.message}</h3>

        <%--Exception: ${exception.message}.
          <c:forEach items="${exception.stackTrace}" var="stackTrace">
             ${stackTrace}
          </c:forEach>--%>
        <img align="center" width="900px" src="${pageContext.request.contextPath}/resources/images/divider2.png">
        <jsp:include page="login-details.jsp"/>
    </div>
    <jsp:include page="${pageContext.request.contextPath}/footer.jsp"/>
</div>
</body>
</html>
