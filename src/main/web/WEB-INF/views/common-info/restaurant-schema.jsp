<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Restaurant schema</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/style.css">
</head>
<body>
<div id="outer">
    <jsp:include page="../header.jsp"/>
    <div id="cont">
        <h1>Restaurant schema</h1>

        <img src="${pageContext.request.contextPath}/${schemaUrl}">
        <img align="center" width="900px" src="${pageContext.request.contextPath}/resources/images/divider2.png">
        <jsp:include page="../login-details.jsp"/>
    </div>

    <jsp:include page="/footer"/>
</div>
</body>
</html>