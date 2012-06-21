<%--
  Created by IntelliJ IDEA.
  User: pritom
  Date: 6/6/12
  Time: 11:06 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>
<head><title>Simple jsp page</title></head>
<body>
<div class="post">
    <form:form commandName="game" method="post">
        <div class="title">
                ${game.gameName}
        </div>
        <div class="entry">

            <img src="images/screenshot.jpg" alt="" height="500" width="800">

            <div class="entry">
                <label style="font-weight:bold"><fmt:message key="game.developer"/>: </label>${game.developer}
                <br>
                <br>
                <label style="font-weight:bold"><fmt:message key="game.synopsis"/>: </label> ${game.synopsis}
                <br>
            </div>

            <table>
                <tr>

                    <td>
                        <table>
                            <tr class="spaceunder2">
                                <td>
                                    <label style="font-weight:bold;"><fmt:message key="game.genre"/>: </label>
                                </td>
                                <td>
                                        ${game.genreString}
                                </td>
                            </tr>
                            <tr class="spaceunder2">
                                <td>
                                    <label style="font-weight:bold;"><fmt:message key="game.platform"/>: </label>
                                </td>
                                <td>
                                        ${game.platform}
                                </td>
                            </tr>
                        </table>
                    </td>
                    <td>
                        <table>
                            <tr class="spaceunder2">
                                <td>
                                    <label style="font-weight:bold;"><fmt:message key="game.releaseDate"/>: </label>
                                </td>
                                <td>
                                        ${game.releaseDate}
                                </td>

                            </tr>
                            <tr class="spaceunder2">
                                <td>
                                    <label style="font-weight:bold;"><fmt:message key="addReview.difficulty"/>: </label>
                                </td>
                                <td>
                                        <%--${game.difficulty}--%>
                                    <c:choose>
                                        <c:when test="${game.difficulty=='1'}">Very Easy
                                        </c:when>

                                        <c:when test="${game.difficulty=='2'}">Easy
                                        </c:when>

                                        <c:when test="${game.difficulty=='3'}">Moderate
                                        </c:when>

                                        <c:when test="${game.difficulty=='4'}">Hard
                                        </c:when>


                                        <c:otherwise>
                                            Very Hard
                                        </c:otherwise>
                                    </c:choose>
                                </td>
                            </tr>
                        </table>
                    </td>

                </tr>


            </table>


            <fieldset>
                <legend>Ratings</legend>
                <table>
                    <tr>
                        <td>
                            <fmt:message key="addReview.presentation"/>
                        </td>
                        <td>
                                ${game.ratingPresentation}
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <fmt:message key="addReview.graphics"/>
                        </td>
                        <td>
                                ${game.ratingGraphics}
                        </td>
                    </tr>

                    <tr>
                        <td>
                            <fmt:message key="addReview.gameplay"/>
                        </td>
                        <td>
                                ${game.ratingGamePlay}
                        </td>
                    </tr>

                    <tr>
                        <td>
                           <fmt:message key="addReview.sound"/>
                        </td>
                        <td>
                                ${game.ratingSound}
                        </td>
                    </tr>

                    <tr>
                        <td>
                            <fmt:message key="addReview.longevity"/>
                        </td>
                        <td>
                                ${game.ratingLongevity}
                        </td>
                    </tr>


                    <tr>
                        <td>
                            <fmt:message key="addReview.gameLength"/>
                        </td>
                        <td>
                                ${game.gameLength}
                        </td>
                    </tr>


                    <tr>
                        <td>
                            <fmt:message key="addReview.overall"/>
                        </td>
                        <td>
                                ${(game.ratingOverall)}
                                ( ${game.voteCount} votes)
                        </td>
                    </tr>

                </table>
            </fieldset>


            <table>
                <tr>
                    <td width="50%"></td>
                    <td width="50%"></td>
                    <td width="50%">
                        <c:if test="${game.played}">
                            <input type="submit" align="right" value="<fmt:message key="game.trackButton"/>" class="nicebutton">
                        </c:if>

                    </td>
                </tr>
            </table>
            <c:if test="${game.tracked}">
                <label style="font-size:15px;color:#0066CC;"><fmt:message key="game.alreadytracked"/></label>
            </c:if>
            <c:if test="${game.played == false && game.tracked==false}">
                <label style="font-size:15px;color:#0066CC;"><fmt:message key="game.alreadyReviewed"/></label>
            </c:if>

        </div>
    </form:form>
</div>
</body>
</html>