<%--
  Created by IntelliJ IDEA.
  User: pritom
  Date: 5/30/12
  Time: 9:47 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head><title>Simple jsp page</title></head>
<body>
<div class="post">
    <div class="title">Review a game<%--${game.gameName} khelechen ?--%>
    </div>
    <div class="entry">
        <form:form method="post" commandName="addReview" action="">
            Select game
            <form:select path="gameId">
                <c:forEach var="game" items="${games}">
                    <form:option value="${game.gameId}">${game.gameName} - ${game.platform}</form:option>
                </c:forEach>
            </form:select>


            <table>
                <tr><br></tr>
                <tr>Review :</tr>
                <tr>
                    <td>
                        <table>

                            <tr>
                                <td>Pros:</td>
                                <td>
                                    <form:textarea path="pros" cols="40" rows="2"/>
                                </td>
                            </tr>
                            <tr>
                                <td>Cons:</td>
                                <td>
                                    <form:textarea path="cons" cols="40" rows="2"/>
                                </td>
                            </tr>
                            <tr>
                                <br>
                            </tr>
                            <tr>
                                <td>
                                    Game Length:
                                </td>
                                <td>
                                    <form:input path="gameLength"/>

                                </td>
                            </tr>
                            <tr>
                                <br>
                            </tr>
                            <tr>
                                <td>
                                    Difficulty
                                </td>
                                <td>
                                    <form:select path="difficulty">
                                        <form:option value="1" label="Very Easy"/>
                                        <form:option value="2" label="Easy"/>
                                        <form:option value="3" label="Moderate"/>
                                        <form:option value="4" label="Hard"/>
                                        <form:option value="5" label="Very Hard"/>
                                    </form:select>
                                </td>
                            </tr>
                        </table>
                    </td>
                    <td>
                        <fieldset>
                            <legend>Ratings</legend>
                            <table>
                                <tr>
                                    <td>
                                        Presentation
                                    </td>
                                    <td>
                                        <form:select path="ratingPresentation">
                                            <form:option value="NONE" label="--- Select ---"/>
                                            <form:options items="${scaleList}"/>
                                        </form:select>
                                    </td>
                                </tr>
                                <tr>
                                    <td>
                                        Graphics
                                    </td>
                                    <td>
                                        <form:select path="ratingGraphics">
                                            <form:option value="NONE" label="--- Select ---"/>
                                            <form:options items="${scaleList}"/>
                                        </form:select>
                                    </td>
                                </tr>

                                <tr>
                                    <td>
                                        Gameplay
                                    </td>
                                    <td>
                                        <form:select path="ratingGamePlay">
                                            <form:option value="NONE" label="--- Select ---"/>
                                            <form:options items="${scaleList}"/>
                                        </form:select>
                                    </td>
                                </tr>

                                <tr>
                                    <td>
                                        Sound
                                    </td>
                                    <td>
                                        <form:select path="ratingSound">
                                            <form:option value="NONE" label="--- Select ---"/>
                                            <form:options items="${scaleList}"/>
                                        </form:select>
                                    </td>
                                </tr>

                                <tr>
                                    <td>
                                        Longevity
                                    </td>
                                    <td>
                                        <form:select path="ratingLongevity">
                                            <form:option value="NONE" label="--- Select ---"/>
                                            <form:options items="${scaleList}"/>
                                        </form:select>
                                    </td>
                                </tr>


                            </table>
                        </fieldset>
                    </td>
                </tr>
                <tr>
                    <td width="50%"></td>

                    <td width="50%">
                        <table>
                            <tr>
                                <td width="50%"></td>
                                <td width="50%"></td>
                                <td width="50%">
                                    <input type="submit" align="right" value="Submit" class="nicebutton">
                                </td>
                            </tr>
                        </table>
                    </td>
                </tr>
            </table>
        </form:form>
    </div>
</div>
</body>
</html>