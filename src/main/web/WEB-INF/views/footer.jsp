<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>footer</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/style.css">
    <link href='http://fonts.googleapis.com/css?family=Dancing+Script' rel='stylesheet' type='text/css'>
</head>
<body>

<div id="footer">
    <table width="100%" cellpadding="5px">
        <tr>
            <th><h1>Restaurant ${info.name}</h1></th>
            <th colspan="2"><h1>Contact details</h1></th>
            <th><h1>How to find us</h1></th>
        </tr>

        <tr>
            <td rowspan="4" valign="top">
                Lorem ipsum dolor sit amet, consectetuer <br>
                adipiscing elit, sed diam nonummy nibh <br>
                euismod tincidunt ut laoreet dolore magna<br>
                aliquam erat volutpat. Ut wisi enim ad minim <br>
                veniam, quis nostrud exerci tation ullamcorper<br>
                suscipit lobortis nisl ut aliquip ex ea commodo <br>
                consequat.
            </td>

            <td><img src="${pageContext.request.contextPath}/resources/images/phone.png"></td>
            <td>1-800-123-4567</td>

            <td rowspan="4" valign="top">
                <iframe width="350" height="200" frameborder="0" style="border:0"
                        src="https://www.google.com/maps/embed/v1/place?q=place_id:ChIJDxj8gJFZwokRh6OYC2khcys&key=AIzaSyCQsN2kbRJa6ByfD3nH8582ATmyyB_etug"></iframe>
            </td>
        </tr>

        <tr>
            <td><img src="${pageContext.request.contextPath}/resources/images/email.png"></td>
            <td>taivas.site@gmail.com</td>
        </tr>
        <tr>
            <td><img src="${pageContext.request.contextPath}/resources/images/address.png"></td>
            <td>1119 Macdougal St, New York,<br>NY 10012, USA</td>
        </tr>
        <tr>
            <td><img src="${pageContext.request.contextPath}/resources/images/schema.png"></td>
            <td><a
                    href="${pageContext.request.contextPath}/restaurant-schema">Restaurant schema</a></td>
        </tr>
    </table>
</div>
</body>
</html>