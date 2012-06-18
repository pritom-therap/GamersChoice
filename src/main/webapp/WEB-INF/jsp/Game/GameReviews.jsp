<%--
  Created by IntelliJ IDEA.
  User: tahmid
  Date: 6/14/12
  Time: 6:47 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head><title>Simple jsp page</title></head>
<body>
<div class="post">
    <div class="title">You have reviewed the following games:</div>

    <div class="entry">
        <table class="playedGameTable">
            <tr>
                <th>
                    Game
                </th>
                <th>
                    Presentation
                </th>
                <th>
                    Graphics
                </th>
                <th>
                    Gameplay
                </th>
                <th>
                    Sound
                </th>
                <th>
                    Lasting Appeal
                </th>
            </tr>
            <c:forEach items="${playedGames}" var="playedGame">
                <c:url value="/Game.htm" var="displayURL">
                    <c:param name="gameId" value="${playedGame.game.gameId}"/>
                </c:url>
                <tr>
                    <td>
                        <a href="${displayURL}">${playedGame.game.gameName}</a>
                    </td>

                    <td>
                            ${playedGame.ratingPresentation}
                    </td>
                    <td>
                            ${playedGame.ratingGraphics}
                    </td>
                    <td>
                            ${playedGame.ratingGamePlay}
                    </td>
                    <td>
                            ${playedGame.ratingSound}
                    </td>
                    <td>
                            ${playedGame.ratingLongevity}
                    </td>
                </tr>
                <br>
            </c:forEach>
        </table>
    </div>

</div>
</body>
</html>