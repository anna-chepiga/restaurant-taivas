<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Log in</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/style.css">
</head>
<body>
<div id="outer">
    <jsp:include page="../header.jsp"/>
    <div id="cont">
        <form:form action="${pageContext.request.contextPath}/login" method="post" commandName="userDTO">
            <p>Username: <form:input path="username" type="text" name="username"/></p>
            <p>Password: <form:input path="password" type="password" name="password"/></p>
            <p><input type="submit" value="Log in"></p>
        </form:form>


        <p>Do not have an account yet? <a href="${pageContext.request.contextPath}/register">Register now!</a> </p>
        <img align="center" width="900px" src="${pageContext.request.contextPath}/resources/images/divider2.png">
    </div>

    <jsp:include page="/footer"/>
</div>
</body>

</html>