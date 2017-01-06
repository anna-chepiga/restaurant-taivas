<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add new ingredient</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/style.css">
</head>
<body>
<div id="outer">
    <jsp:include page="../../header.jsp"/>
    <div id="cont">
        <form action="${pageContext.request.contextPath}/admin/add-new-ingredient" method="post">
            <p>Enter name: <input type="text" name="ingredientName"></p>
            <p>Enter amount: <input type="number" name="amount"></p>
            <p><input type="submit" value="Add"></p>
        </form>
        <img align="center" width="900px" src="${pageContext.request.contextPath}/resources/images/divider2.png">
        <jsp:include page="../../login-details.jsp"/>
    </div>

    <jsp:include page="../../footer.jsp"/>
</div>
</body>
</html>
