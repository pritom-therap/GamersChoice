<%--
  Created by IntelliJ IDEA.
  User: pritom
  Date: 6/18/12
  Time: 2:33 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="/jmesa" prefix="jmesa" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head><title>Simple jsp page</title></head>
<body>
<h2 style="background-color:#1b63d2;color:#dedef5;font-weight:bold;">
    Top Games
</h2>

<form name="TopGameForm">


    <jmesa:tableModel id="tag" items="${topGames}" var="game">

        <c:url value="/Game.htm" var="displayURL">
            <c:param name="gameId" value="${game.gameId}"/>
        </c:url>

        <jmesa:htmlTable width="200px" styleClass="jmesa" cellpadding="2px" cellspacing="1px">
            <jmesa:htmlRow>
                <jmesa:htmlColumn property="gameName" style="font-size:10px">
                    <a href="${displayURL}">${game.gameName}</a>
                </jmesa:htmlColumn>

                <jmesa:htmlColumn title="Rating" style="font-size:10px">
                    <%--${(game.ratingPresentation+game.ratingGraphics+game.ratingGamePlay+game.ratingSound+game.ratingLongevity)/5}--%>
                    ${game.ratingOverall}
                </jmesa:htmlColumn>
            </jmesa:htmlRow>
        </jmesa:htmlTable>
    </jmesa:tableModel>
</form>
</body>
</html>