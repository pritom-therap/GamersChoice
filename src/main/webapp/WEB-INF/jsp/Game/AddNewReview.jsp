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
        <form:form method="post" commandName="reviewCmd" action="">
            <label style="font-weight:bold;">Select game</label>
            <form:select path="gameId">
                <c:forEach var="game" items="${games}">
                    <form:option value="${game.gameId}">${game.gameName} - ${game.platform}</form:option>
                </c:forEach>
            </form:select>


            <table>
                <tr><br></tr>
                <tr>
                    <label style="font-weight:bold;">Review :</label>
                </tr>
                <tr>
                    <td>
                        <table>

                            <tr class="spaceunder2">
                                <td>
                                    <label style="font-weight:bold;">Pros:</label>
                                </td>
                                <td>
                                    <form:textarea path="pros" cols="40" rows="2"/>
                                    <form:errors path="pros" cssClass="error"/>
                                </td>

                            </tr>
                            <tr class="spaceunder2">
                                <td>
                                    <label style="font-weight:bold;">Cons:</label>
                                </td>
                                <td>
                                    <form:textarea path="cons" cols="40" rows="2"/>
                                    <form:errors path="cons" cssClass="error"/>
                                </td>
                            </tr>
                            <tr>
                                <br>
                            </tr>
                            <tr class="spaceunder2">
                                <td>
                                    <label style="font-weight:bold;">Game Length:</label>
                                </td>
                                <td>
                                    <form:input path="gameLength"/>
                                    <form:errors path="gameLength" cssClass="error"/>
                                </td>
                            </tr>
                            <tr>
                                <br>
                            </tr>
                            <tr class="spaceunder2">
                                <td>
                                    <label style="font-weight:bold;">Difficulty:</label>
                                </td>
                                <td>
                                    <form:select path="difficulty">
                                        <form:option value="-1" label="--- Select ---"/>
                                        <form:option value="1" label="Very Easy"/>
                                        <form:option value="2" label="Easy"/>
                                        <form:option value="3" label="Moderate"/>
                                        <form:option value="4" label="Hard"/>
                                        <form:option value="5" label="Very Hard"/>
                                    </form:select>
                                    <form:errors path="difficulty" cssClass="error"/>
                                </td>
                            </tr>
                        </table>
                    </td>

                </tr>
            </table>
            <br>


            <fieldset>
                <legend>Ratings</legend>
                <table>
                    <tr>
                        <td>
                            <label style="font-weight:bold;">Presentation</label>
                        </td>
                        <td>
                            <form:select path="ratingPresentation">
                                <form:option value="-1.0" label="--- Select ---"/>
                                <form:options items="${scaleList}"/>
                            </form:select>
                            <form:errors path="ratingPresentation" cssClass="error"/>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <label style="font-weight:bold;">Graphics</label>
                        </td>
                        <td>
                            <form:select path="ratingGraphics">
                                <form:option value="-1.0" label="--- Select ---"/>
                                <form:options items="${scaleList}"/>
                            </form:select>
                            <form:errors path="ratingGraphics" cssClass="error"/>
                        </td>
                    </tr>

                    <tr>
                        <td>
                            <label style="font-weight:bold;">Gameplay</label>
                        </td>
                        <td>
                            <form:select path="ratingGamePlay">
                                <form:option value="-1.0" label="--- Select ---"/>
                                <form:options items="${scaleList}"/>
                            </form:select>
                            <form:errors path="ratingGamePlay" cssClass="error"/>
                        </td>
                    </tr>

                    <tr>
                        <td>
                            <label style="font-weight:bold;">Sound</label>
                        </td>
                        <td>
                            <form:select path="ratingSound">
                                <form:option value="-1.0" label="--- Select ---"/>
                                <form:options items="${scaleList}"/>
                            </form:select>
                            <form:errors path="ratingSound" cssClass="error"/>
                        </td>
                    </tr>

                    <tr>
                        <td>
                            <label style="font-weight:bold;">Longevity</label>
                        </td>
                        <td>
                            <form:select path="ratingLongevity">
                                <form:option value="-1.0" label="--- Select ---"/>
                                <form:options items="${scaleList}"/>
                            </form:select>
                            <form:errors path="ratingLongevity" cssClass="error"/>
                        </td>
                    </tr>


                </table>
            </fieldset>
            <table>
                <tr>
                    <table>
                        <tr>
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