<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Register</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/style.css">
</head>
<body>
<div id="outer">
    <jsp:include page="../header.jsp"/>
    <div id="cont">
        <h2>Register</h2>

        <form:form action="${pageContext.request.contextPath}/register" method="post" commandName="userDTO">
            <p>* Required fields</p>
            <table border="0">
                <tr>
                    <td>Username*:</td>
                    <td><form:input path="username" type="text" name="username"/></td>
                </tr>
                <tr>
                    <td>Password*:</td>
                    <td><form:input path="password" type="password" name="password"/></td>
                </tr>
                <tr>
                    <td>Confirm password*:</td>
                    <td><input type="password" name="confirm_password" title="pass"/></td>
                </tr>
                <tr>
                    <td>Email*:</td>
                    <td><form:input path="email" type="email" name="email"/></td>
                </tr>
                <tr>
                    <td>Your name:</td>
                    <td><form:input path="name" type="text" name="name"/></td>
                </tr>
            </table>

            <input type="submit" value="Register">
        </form:form>
        <img align="center" width="900px" src="${pageContext.request.contextPath}/resources/images/divider2.png">
    </div>

    <jsp:include page="/footer"/>
</div>
</body>

</html>