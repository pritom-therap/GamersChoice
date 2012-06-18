<%--
  Created by IntelliJ IDEA.
  User: pritom
  Date: 6/7/12
  Time: 3:09 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
<title>Simple jsp page</title>


</head>
<body>
<div class="post">
    <div class="title">
        Please log in<%--${game.gameName} khelechen ?--%>
        <br>
        <br>
    </div>

    <div class="entry">

        <form:form method="post" commandName="loginCmd" action="">
            <table>
                <tr class="spaceunder2">
                    <td>
                        Email :
                    </td>
                    <td>
                        <form:input path="email"/>
                    </td>
                    <td>
                        <form:errors path="email" cssClass="error"/>
                    </td>
                </tr>

                <tr class="spaceunder2">
                    <td>
                        Password :
                    </td>
                    <td>

                        <form:password path="password"/>
                    </td>
                    <td>
                        <form:errors path="password" cssClass="error"/>
                    </td>
                </tr>

                <tr>


                    <td>
                        <input type="submit" align="right" value="Login" class="nicebutton">
                    </td>
                </tr>
            </table>
        </form:form>


        <c:if test="${not empty message}">
            <label style="color:red;">${message}</label>
        </c:if>
    </div>
</div>
</body>
</html>