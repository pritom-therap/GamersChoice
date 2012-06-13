<%--
  Created by IntelliJ IDEA.
  User: pritom
  Date: 6/10/12
  Time: 3:20 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="/jmesa" prefix="jmesa" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Simple jsp page</title>
    <script type="text/javascript">
        function onInvokeAction(id) {
            createHiddenInputFieldsForLimitAndSubmit(id);
        }
    </script>


</head>
<body>
<div class="post">
    <div class="title">Tracked games<%--${game.gameName} khelechen ?--%>
    </div>
    <%--<div class="entry">--%>
    <form name="GameForm">


        <jmesa:tableModel id="tag" items="${trackedGames}" var="game">

            <c:url value="/Game.htm" var="displayURL">
                <c:param name="gameId" value="${game.gameId}"/>
            </c:url>

            <jmesa:htmlTable width="800px" styleClass="jmesa" cellpadding="15px" cellspacing="5px">
                <jmesa:htmlRow>
                    <jmesa:htmlColumn property="gameName">
                        <a href="${displayURL}">${game.gameName}</a>
                    </jmesa:htmlColumn>

                    <jmesa:htmlColumn property="developer"/>
                    <jmesa:htmlColumn property="platform"/>
                    <jmesa:htmlColumn property="genreString" title="Genres(s)"/>
                    <jmesa:htmlColumn title="Rating">
                        ${(game.ratingPresentation+game.ratingGraphics+game.ratingGamePlay+game.ratingSound+game.ratingLongevity)/5}
                    </jmesa:htmlColumn>
                </jmesa:htmlRow>
            </jmesa:htmlTable>
        </jmesa:tableModel>
    </form>



    <fieldset>
        <legend><h2>Tracked Users</h2></legend>
        <c:forEach items="${trackedUsers}" var="trackedUser">
            <fieldset style="width:800px">
                <legend><h3>${trackedUser.userName} recently played:</h3></legend>

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
                    <c:forEach items="${trackedUser.playedGames}" var="playedGame">
                        <tr>
                            <td>
                                   ${playedGame.game.gameName}
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

            </fieldset>
            <br>
        </c:forEach>

    </fieldset>


    <%--</div>--%>
</div>
</body>
</html>