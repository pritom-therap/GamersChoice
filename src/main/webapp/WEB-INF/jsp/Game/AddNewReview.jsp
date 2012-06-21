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
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>
<head><title>Simple jsp page</title></head>
<body>
<div class="post">
    <div class="title"><fmt:message key="addReview.title"/><%--${game.gameName} khelechen ?--%>
    </div>
    <div class="entry">
        <form:form method="post" commandName="reviewCmd" action="">
            <table>
                <tr>
                    <td width="45%">
                        <label style="font-weight:bold;"><fmt:message key="addReview.select"/></label>
                    </td>
                    <td width="55%">
                        <form:select path="gameId">
                            <c:forEach var="game" items="${games}">
                                <form:option value="${game.gameId}">${game.gameName} - ${game.platform}</form:option>
                            </c:forEach>
                        </form:select>
                    </td>
                </tr>
            </table>

            <table>
                <tr><br></tr>
                <tr>
                    <label style="font-weight:bold;"><fmt:message key="addReview.review"/></label>
                </tr>
                <tr>
                    <td>
                        <table>

                            <tr class="spaceunder2">
                                <td>
                                    <label style="font-weight:bold;"><fmt:message key="addReview.pros"/>:</label>
                                </td>
                                <td>
                                    <form:textarea path="pros" cols="40" rows="2"/>
                                    <form:errors path="pros" cssClass="error"/>
                                </td>

                            </tr>
                            <tr class="spaceunder2">
                                <td>
                                    <label style="font-weight:bold;"><fmt:message key="addReview.cons"/>:</label>
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
                                    <label style="font-weight:bold;"><fmt:message key="addReview.gameLength"/>:</label>
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
                                    <label style="font-weight:bold;"><fmt:message key="addReview.difficulty"/>:</label>
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
                            <label style="font-weight:bold;"><fmt:message key="addReview.presentation"/></label>
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
                            <label style="font-weight:bold;"><fmt:message key="addReview.graphics"/></label>
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
                            <label style="font-weight:bold;"><fmt:message key="addReview.gameplay"/></label>
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
                            <label style="font-weight:bold;"><fmt:message key="addReview.sound"/></label>
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
                            <label style="font-weight:bold;"><fmt:message key="addReview.longevity"/></label>
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
                                <input type="submit" align="right" value="<fmt:message key="addReview.submit"/>"
                                       class="nicebutton">
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