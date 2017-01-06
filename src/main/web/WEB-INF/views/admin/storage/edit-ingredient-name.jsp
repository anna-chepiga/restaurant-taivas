<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Edit ingredient name: ${oldName}</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/style.css">
</head>
<body>
<div id="outer">
    <jsp:include page="../../header.jsp"/>
    <div id="cont">
        <p>Edit ingredient: <span id="hdrname">${oldName}</span> </p>

        <form action="${pageContext.request.contextPath}/admin/edit-ingredient-name/${oldName}" method="post">
            <p>Enter new name:</p>
            <p><input type="text" name="newName"></p>
            <p><input type="submit" value="Edit"></p>
        </form>
        <img align="center" width="900px" src="${pageContext.request.contextPath}/resources/images/divider2.png">
        <jsp:include page="../../login-details.jsp"/>
    </div>

    <jsp:include page="../../footer.jsp"/>
</div>
</body>
</html>
