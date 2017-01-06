<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Welcome</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/style.css">
</head>
<body>

<div id="outer">
    <jsp:include page="header.jsp"/>

    <div id="nav">
        <ul class="main-menu">
            <li><a href="${pageContext.request.contextPath}/employees">Our personnel</a></li>
            <li>
                <a href="${pageContext.request.contextPath}/all-dishes">Menu</a>
                <ul class="sub-menu">
                    <c:forEach items="${menuNames}" var="menu">
                        <li><a href="${pageContext.request.contextPath}/menu/${menu}">${menu}</a></li>
                    </c:forEach>
                </ul>
            </li>
            <li>
                <a href="${pageContext.request.contextPath}/all-dishes">Categories</a>
                <ul class="sub-menu">
                    <c:forEach items="${categoryNames}" var="category">
                        <li><a href="${pageContext.request.contextPath}/category/${category}">${category}</a></li>
                    </c:forEach>
                </ul>
            </li>
            <li>
                <a href="${pageContext.request.contextPath}/contacts">Contacts</a>
            </li>
        </ul>

    </div>

    <div id="cont">

        <div id="tbl">
            <h1>Our most popular dishes!</h1>
            <table width="650px" align="center">
                <tr>
                    <c:forEach items="${popularDishes}" var="dish">
                        <td>
                            <img width="150px" src="${pageContext.request.contextPath}/${dish.photoUrl}"><br>
                            <a href="${pageContext.request.contextPath}/dish/${dish.dishName}">${dish.dishName}</a>
                            <p>${dish.weight} gr.</p>
                            <p>${dish.price} USD</p>
                        </td>
                    </c:forEach>
                </tr>
            </table>
        </div>

        <div id="cont2">
            <a href="${pageContext.request.contextPath}/all-dishes">See all dishes!</a>

            <p>Find dish by name:</p>

            <form:form action="${pageContext.request.contextPath}/find-dish" method="get" modelAttribute="dishDTO" autocomplete="false">
                <form:input name="dishName" path="dishName" list="dishList"/>
                <datalist id="dishList">
                    <c:forEach items="${allDishes}" var="dish">
                        <option>${dish.dishName}</option>
                    </c:forEach>
                </datalist>
                <input type="submit" value="Find">
            </form:form>

            <p>Lorem ipsum dolor sit amet,<br>
                consectetuer adipiscing elit, <br>
                sed diam nonummy nibh euismod <br>
                tincidunt ut laoreet dolore <br>
                magna aliquam erat volutpat. <br>
                Ut wisi enim ad minim veniam, <br>
                quis nostrud exerci tation <br>
                ullamcorpersuscipit lobortis <br>
                nisl ut aliquip ex ea <br>
                commodo consequat.</p>
        </div>


        <img align="center" width="900px" src="${pageContext.request.contextPath}/resources/images/divider2.png">
        <jsp:include page="login-details.jsp"/>

    </div>

    <jsp:include page="${pageContext.request.contextPath}/footer.jsp"/>
</div>

</body>
</html>
