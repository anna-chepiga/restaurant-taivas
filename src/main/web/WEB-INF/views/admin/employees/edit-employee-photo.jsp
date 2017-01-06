<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Edit employee photo</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/style.css">
</head>
<body>
<div id="outer">
    <jsp:include page="../../header.jsp"/>
    <div id="cont">
        <p>Upload new photo for employee ${employeeId}</p>
        <form action="${pageContext.request.contextPath}/admin/edit-employee-photo/${employeeId}" method="post" enctype="multipart/form-data">
            <input type="file" name="photo" accept="image/gif, image/jpeg, image/png">
            <input type="submit" value="Save">
        </form>
        <img align="center" width="900px" src="${pageContext.request.contextPath}/resources/images/divider2.png">
        <jsp:include page="../../login-details.jsp"/>
    </div>

    <jsp:include page="${pageContext.request.contextPath}/footer.jsp"/>
</div>
</body>
</html>
