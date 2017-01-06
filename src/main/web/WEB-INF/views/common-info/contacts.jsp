<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Contacts</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/style.css">
</head>
<body>
<div id="outer">
    <jsp:include page="../header.jsp"/>
    <div id="cont">
        <table border="0">
            <tr>
                <td><img src="${pageContext.request.contextPath}/resources/images/address.png"></td>
                <td>${address}</td>
            </tr>
            <tr>
                <td><img src="${pageContext.request.contextPath}/resources/images/email.png"></td>
                <td>${email}</td>
            </tr>
            <tr>
                <td><img src="${pageContext.request.contextPath}/resources/images/phone.png"></td>
                <td>${phone}</td>
            </tr>
        </table>

        <form action="${pageContext.request.contextPath}/contacts" method="post">
            <p>Your name: <input type="text" title="name" name="user_name"></p>

            <p>Your comment:</p>
            <p><textarea name="comment" cols="40" rows="4" title="comment"></textarea></p>

            <p><input type="submit" value="Send feedback"></p>

        </form>
        <img align="center" width="900px" src="${pageContext.request.contextPath}/resources/images/divider2.png">
        <jsp:include page="../login-details.jsp"/>
    </div>

    <jsp:include page="${pageContext.request.contextPath}/footer.jsp"/>
</div>
</body>
</html>