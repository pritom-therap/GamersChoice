<%--
  Created by IntelliJ IDEA.
  User: pritom
  Date: 6/7/12
  Time: 12:07 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head><title>Simple jsp page</title></head>
<body>
<div class="post">
    <div class="title">Register<%--${game.gameName} khelechen ?--%>
    </div>
    <div class="entry">
        <form:form method="post" commandName="userCmd" action="">
            <table>
                <tr class="spaceunder2">
                    <td width="50%">
                        User Name :

                    </td>
                    <td width="50%">
                        <form:input path="userName"/>
                    </td>
                    <td width="50%">
                        <form:errors path="userName" cssClass="error"/>
                    </td>
                </tr>
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
                <tr class="spaceunder2">
                    <td>
                        Confirmed Password :
                    </td>
                    <td>
                        <form:password path="confirmPassword"/>
                    </td>

                    <td>
                        <form:errors path="confirmPassword" cssClass="error"/>
                    </td>
                </tr>


            </table>

            <table>
                <tr>
                    <td>
                        <form:checkbox path="agreeRules"/>I agree to follow all the rules and regulations of the Admins
                        and this website


                    </td>
                </tr>
                 <tr>
                    <td>
                        <form:errors path="agreeRules" cssClass="error"/>
                    </td>
                </tr>
                <tr>
                    <td>
                        <form:checkbox path="agreeAgeLimit"/>
                        I am over 12 years old

                    </td>
                </tr>
                <tr>
                    <td>
                        <form:errors path="agreeAgeLimit" cssClass="error"/>
                    </td>
                </tr>

                <tr>
                    <td width="50%">
                        <input type="submit" align="right" value="Register" class="nicebutton">
                    </td>
                </tr>
            </table>

        </form:form>
    </div>
</div>

</body>
</html>