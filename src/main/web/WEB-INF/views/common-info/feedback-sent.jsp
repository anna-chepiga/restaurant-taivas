<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Thank you!</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/style.css">
</head>
<body>
<div id="outer">
    <jsp:include page="../header.jsp"/>
    <div id="cont">
        <h3>Thank you for your feedback, ${user_name}!</h3>

        <p>Your message is: </p>

        <p>${comment}</p>

        <p>It will be processed by us as soon as possible! =)</p>
        <img align="center" width="900px" src="${pageContext.request.contextPath}/resources/images/divider2.png">
        <jsp:include page="../login-details.jsp"/>
    </div>

    <jsp:include page="${pageContext.request.contextPath}/footer.jsp"/>
</div>
</body>
</html>