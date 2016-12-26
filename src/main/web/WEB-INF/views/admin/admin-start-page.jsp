<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Admin</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/style.css">
</head>
<body>
<div id="outer">
    <jsp:include page="../header.jsp"/>
    <div id="cont">
        <h1>Admin start page</h1>

        <p>Work with:</p>

        <ul>
            <li><a href="${pageContext.request.contextPath}/admin/manage-menus">Menus</a></li>
            <li><a href="${pageContext.request.contextPath}/admin/manage-dishes">Dishes</a></li>
            <li><a href="${pageContext.request.contextPath}/admin/manage-employees">Personnel</a></li>
            <li><a href="${pageContext.request.contextPath}/admin/manage-storage">Storage</a></li>
            <li><a href="${pageContext.request.contextPath}/admin/manage-orders">Orders history</a></li>
        </ul>
        <img align="center" width="900px" src="${pageContext.request.contextPath}/resources/images/divider2.png">
        <jsp:include page="../login-details.jsp"/>
    </div>

    <jsp:include page="/footer"/>
</div>
</body>
</html>
