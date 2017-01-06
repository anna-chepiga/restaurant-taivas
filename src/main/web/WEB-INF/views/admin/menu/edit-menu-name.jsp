<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Edit menu: ${oldName}</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/style.css">
</head>
<body>
<div id="outer">
    <jsp:include page="../../header.jsp"/>
    <div id="cont">
        <p>Edit menu: <span id="hdrname">${oldName}</span></p>

        <form:form action="${pageContext.request.contextPath}/admin/edit-menu-name/${oldName}" method="post" commandName="menuDTO">
            <p>Enter new name:</p>
            <p><form:input path="menuName" type="text"/></p>
            <p><input type="submit" value="Edit"></p>
        </form:form>
        <img align="center" width="900px" src="${pageContext.request.contextPath}/resources/images/divider2.png">
        <jsp:include page="../../login-details.jsp"/>
    </div>

    <jsp:include page="../../footer.jsp"/>
</div>
</body>
</html>