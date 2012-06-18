<%--
  Created by IntelliJ IDEA.
  User: pritom
  Date: 6/5/12
  Time: 12:54 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@page pageEncoding="UTF-8" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Simple jsp page</title>
</head>
<body>
<div class="post">
    <div class="title">Add a new game<%--${game.gameName} khelechen ?--%>
    </div>
    <div class="entry">
        <form:form method="POST" commandName="gameCmd" enctype="multipart/form-data">
            <table>
                <tr class="spaceunder2">
                    <td>
                        Name of Game:
                    </td>
                    <td>
                        <form:input path="gameName"></form:input>
                    </td>
                    <td>
                        <form:errors path="gameName" cssClass="error"/>
                    </td>

                </tr>
                <tr class="spaceunder2">
                    <td>
                        Developer:
                    </td>
                    <td>
                        <form:input path="developer"></form:input>
                    </td>
                    <td>
                        <form:errors path="developer" cssClass="error"/>
                    </td>
                </tr>
                <tr class="spaceunder2">
                    <td>
                        Upload screenshot :
                    </td>
                    <td>
                        <input type="file" name="file" />
                    </td>
                    <td>
                        <form:errors path="file" cssClass="error"/>
                    </td>
                </tr>
                <tr class="spaceunder2">
                    <td>
                        Synopsis:
                    </td>
                    <td>
                        <form:textarea path="synopsis" rows="10" cols="60"></form:textarea>
                    </td>
                    <td>
                        <form:errors path="synopsis" cssClass="error"/>
                    </td>
                </tr>
                <tr class="spaceunder2">
                    <td>
                        Platform:
                    </td>
                    <td>
                        <form:select path="platform">
                            <form:option value="PC" label="PC"/>
                            <form:option value="PS3" label="PS3"/>
                            <form:option value="XBox360" label="XBox360"/>
                        </form:select>
                    </td>
                </tr>
                <tr class="spaceunder2">
                    <td>
                       Genre(s):
                    </td>
                    <td>
                        <form:checkboxes path="genre" items="${genreList}" delimiter="<br>"/>
                    </td>
                    <td>
                        <form:errors path="genre" cssClass="error"/>
                    </td>
                </tr>

                <tr class="spaceunder2">
                    <td>
                        <input type="submit" value="Add Game" class="nicebutton"/>
                    </td>
                </tr>
            </table>





        </form:form>
    </div>
</div>
</body>
</html>