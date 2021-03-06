<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Edit email</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/style.css">
</head>

<body>
<div id="outer">
    <jsp:include page="../header.jsp"/>
    <div id="cont">
        <form action="${pageContext.request.contextPath}/account/edit-email/${username}" method="post">
            <p>Enter your new email:</p>
            <p><input type="email" name="newEmail"></p>
            <p><input type="submit" value="Edit"></p>
        </form>
        <img align="center" width="900px" src="${pageContext.request.contextPath}/resources/images/divider2.png">
        <jsp:include page="../login-details.jsp"/>
    </div>

    <jsp:include page="../footer.jsp"/>
</div>
</body>

</html>
