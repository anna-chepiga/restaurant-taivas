<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Registration successful!</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/style.css">
</head>
<body>
<div id="outer">
    <jsp:include page="../header.jsp"/>
    <div id="cont">
        <h1>Registration successful!</h1>

        <p>Thank you for registering!</p>
        <p>Here is the review of your details:</p>

        <p>
            Username: ${userDTO.username} <br>
            Email: ${userDTO.email} <br>
            Name: ${userDTO.name}
        </p>

        <p><a href="${pageContext.request.contextPath}/account/${userDTO.username}">Proceed to your cabinet</a>
            or <a href="${pageContext.request.contextPath}/">go to main page</a></p>
        <img align="center" width="900px" src="${pageContext.request.contextPath}/resources/images/divider2.png">
        <jsp:include page="../login-details.jsp"/>
    </div>

    <jsp:include page="${pageContext.request.contextPath}/footer.jsp"/>
</div>
</body>

</html>
