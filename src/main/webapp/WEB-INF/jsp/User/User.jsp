<%--
  Created by IntelliJ IDEA.
  User: pritom
  Date: 6/13/12
  Time: 9:40 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="/jmesa" prefix="jmesa" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>
<head><title>Simple jsp page</title></head>
<body>

<div class="post">

    <form:form commandName="requestedUser" method="post">
        <div class="title">${requestedUser.userName}<%--${game.gameName} khelechen ?--%>
        </div>

        <div>


            <jmesa:tableModel id="tag" items="${playedGames}" var="playedGame">

                <c:url value="/Game.htm" var="displayURL">
                    <c:param name="gameId" value="${playedGame.gameId}"/>
                </c:url>

                <jmesa:htmlTable width="800px" styleClass="jmesa" cellpadding="15px" cellspacing="5px">
                   <jmesa:htmlRow>
                    <jmesa:htmlColumn property="gameName">
                        <a href="${displayURL}">${playedGame.gameName}</a>
                    </jmesa:htmlColumn>

                    <jmesa:htmlColumn property="developer"/>
                    <jmesa:htmlColumn property="platform"/>
                    <jmesa:htmlColumn property="genreString" title="Genre(s)"/>
                    <jmesa:htmlColumn title="Rating">
                        <%--${(game.ratingPresentation+game.ratingGraphics+game.ratingGamePlay+game.ratingSound+game.ratingLongevity)/5}--%>
                        ${playedGame.ratingOverall}
                    </jmesa:htmlColumn>
                </jmesa:htmlRow>
                </jmesa:htmlTable>
            </jmesa:tableModel>

        </div>

        <table>
            <tr>
                <td width="50%"></td>
                <td width="50%"></td>
                <td width="50%">
                    <c:if test="${requestedUser.approved==false && requestedUser.requested==false && requestedUser.rejected==false}">
                        <input type="submit" align="right" value="Track" class="nicebutton">
                    </c:if>

                </td>
            </tr>
        </table>
    </form:form>

    <c:if test="${requestedUser.approved}">
                <label style="font-size:15px;color:#0066CC;">You are already tracking ${requestedUser.userName}</label>
            </c:if>
            <c:if test="${requestedUser.requested}">
                <label style="font-size:15px;color:#0066CC;">Your have requested to track ${requestedUser.userName}</label>
            </c:if>
             <c:if test="${requestedUser.rejected}">
                <label style="font-size:15px;color:#0066CC;">${requestedUser.userName} has rejected your request</label>
            </c:if>
</div>

</body>
</html>