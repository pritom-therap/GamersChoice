<%--
  Created by IntelliJ IDEA.
  User: pritom
  Date: 5/30/12
  Time: 9:47 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://www.opensymphony.com/sitemesh/decorator" prefix="decorator" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="/jmesa" prefix="jmesa" %>


<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="content-type" content="text/html; charset=utf-8"/>
    <title>Gamer's Choice - The power is in your hands</title>
    <meta name="keywords" content=""/>
    <meta name="description" content=""/>
    <link href="css/style.css" rel="stylesheet" type="text/css"/>
    <link rel="stylesheet" type="text/css" href="css/jmesa.css"/>
    <link rel="stylesheet" type="text/css" href="css/smoothDivScroll.css">
    <%--<link rel="stylesheet" type="text/css" href="css/sjmesa.css"/>--%>
</head>
<body>
<div id="wrapper">
    <div id="logo">
        <h1><a href="#">Gamer's Choice </a></h1>

        <p><em> site developed <%--<a href="http://www.freecsstemplates.org/">Free CSS Templates</a>--%></em></p>
    </div>
    <hr/>
    <!-- end #logo -->
    <div id="header">
        <div id="menu">
            <ul>
                <li><a href="/gamerschoice/GamersChoice.htm">Suggestions</a></li>
                <li id="menuItem1"><a href="/gamerschoice/Games.htm">Games</a></li>
                <li><a href="/gamerschoice/AddNewReview.htm">Review Game</a></li>
                <li><a href="/gamerschoice/TrackedItems.htm">Tracked Items</a></li>

            </ul>
        </div>
        <!-- end #menu -->
        <div id="search">
            <%--<form method="get" action="">
                   <fieldset>
                   <input type="text" name="s" id="search-text" size="15" />
                   <input type="submit" id="search-submit" value="GO" />
                   </fieldset>
               </form>--%>
            <c:if test="${not empty User}">
                <label style="font-size:15px; color:#FFFFFF">
                    Hello, ${User.userName} <a href="/gamerschoice/Logout.htm">Log out</a>
                </label>
            </c:if>
        </div>
        <!-- end #search -->
    </div>
    <!-- end #header -->
    <!-- end #header-wrapper -->
    <div id="page">
        <div id="page-bgtop">
            <div id="content">
                <div>
                    <%--<h2 class="title"><a href="#">Welcome to Condition </a></h2>
                     <p class="meta">Sunday, April 26, 2009 7:27 AM Posted by <a href="#">Someone</a></p>
                     <div class="entry">
                         <p>This is <strong>Condition </strong>, a free, fully standards-compliant CSS template designed by <a href="http://www.freecsstemplates.org/">Free CSS Templates</a>, released for free under the <a href="http://creativecommons.org/licenses/by/2.5/">Creative Commons Attribution 2.5</a> license.  You're free to use this template for anything as long as you link back to <a href="http://www.freecsstemplates.org/">my site</a>. Enjoy :)</p>
                         <p>Sed lacus. Donec lectus. Nullam pretium nibh ut turpis. Nam bibendum. In nulla tortor, elementum ipsum. Proin imperdiet est. Phasellus dapibus semper urna. Pellentesque ornare, orci in felis. Donec ut ante. In id eros. Suspendisse lacus turpis, cursus egestas at sem.Sed lacus. </p>
                     </div>--%>

                    <decorator:body/>
                </div>
            </div>
            <!-- end #content -->
            <div id="sidebar">
                <ul>
                    <li>
                        <h2>Features</h2>
                        <ul>
                            <c:if test="${not empty User}">
                                <li><a href="/gamerschoice/Games.htm">Games</a></li>
                                <li><a href="/gamerschoice/AddNewReview.htm">Review Game</a></li>
                                <li><a href="/gamerschoice/TrackedItems.htm">Tracked Items</a></li>
                                <li><a href="/gamerschoice/GamersChoice.htm">Gamer's Choice</a></li>
                                <li><a href="/gamerschoice/Users.htm">Members</a></li>
                            </c:if>
                            <c:if test="${empty User}">
                                <li><a href="/gamerschoice/Login.htm">Log In</a></li>
                                <li><a href="/gamerschoice/Register.htm">Register</a></li>
                            </c:if>
                            <%-- <c:forEach items="${topGames}" var="game">
                                <label>${game.gameName} ${game.ratingOverall}</label>
                                <br>
                            </c:forEach>--%>
                        </ul>
                    </li>
                </ul>


                <br>
                <br>

                <h2>
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
            </div>
            <!-- end #sidebar -->
            <div style="clear: both;">&nbsp;</div>
        </div>
    </div>
    <!-- end #page -->
    <div id="footer-bgcontent">
        <div id="footer">
            <p>Copyright (c) 2008 Sitename.com. All rights reserved. Design by <a
                    href="http://www.freecsstemplates.org/">Free CSS Templates</a>.</p>
        </div>
    </div>
    <!-- end #footer -->
</div>

<!-- LOAD JAVASCRIPT LATE - JUST BEFORE CLOSING THE BODY TAG.
That way the browser will have loaded the images and will
know the width of the images. No need to specify the width
in the CSS or inline. -->

<!-- jQuery library - Please load it from Google API's -->

</body>
</html>
