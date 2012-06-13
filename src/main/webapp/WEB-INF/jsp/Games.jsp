<%--
  Created by IntelliJ IDEA.
  User: pritom
  Date: 6/6/12
  Time: 4:46 PM
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
    <div class="title">Games<%--${game.gameName} khelechen ?--%>
    </div>
    <%--<div class="entry">--%>

    <div id="makeMeScrollable">


                <img src="images/demo/field.jpg" alt="Demo image" id="field"/>
                <img src="images/demo/gnome.jpg" alt="Demo image" id="gnome"/>
                <%--<img src="images/demo/pencils.jpg" alt="Demo image" id="pencils"/>
            <img src="images/demo/golf.jpg" alt="Demo image" id="golf"/>
            <img src="images/demo/river.jpg" alt="Demo image" id="river"/>
            <img src="images/demo/train.jpg" alt="Demo image" id="train"/>
            <img src="images/demo/leaf.jpg" alt="Demo image" id="leaf"/>
            <img src="images/demo/dog.jpg" alt="Demo image" id="dog"/>--%>


    </div>
    <form name="GameForm">


        <jmesa:tableModel id="tag" items="${gameList}" var="game">

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
                        <%--${(game.ratingPresentation+game.ratingGraphics+game.ratingGamePlay+game.ratingSound+game.ratingLongevity)/5}--%>
                        ${game.ratingOverall}
                    </jmesa:htmlColumn>
                </jmesa:htmlRow>
            </jmesa:htmlTable>
        </jmesa:tableModel>
    </form>


    <%--</div>--%>
</div>
<script src="//ajax.googleapis.com/ajax/libs/jquery/1.7.1/jquery.min.js" type="text/javascript"></script>

<!-- jQuery UI Widget and Effects Core (custom download)
You can make your own at: http://jqueryui.com/download -->
<script src="js/jquery-ui-1.8.18.custom.min.js" type="text/javascript"></script>

<!-- Latest version of jQuery Mouse Wheel by Brandon Aaron
You will find it here: http://brandonaaron.net/code/mousewheel/demos -->
<script src="js/jquery.mousewheel.min.js" type="text/javascript"></script>

<!-- Smooth Div Scroll 1.2 minified-->
<script src="js/jquery.smoothdivscroll-1.2-min.js" type="text/javascript"></script>

<!-- If you want to look at the uncompressed version you find it at
	    js/jquery.smoothDivScroll-1.2.js -->


<!-- Plugin initialization -->
<script type="text/javascript">
    // Initialize the plugin with no custom options
    $(document).ready(function() {
        $("#makeMeScrollable").smoothDivScroll({});
    });
</script>

<script type="text/javascript">
    // Initialize the plugin with no custom options
    $(document).ready(function () {
        // I just set some of the options
        $("#makeMeScrollable").smoothDivScroll({
            mousewheelScrolling: true,
            manualContinuousScrolling: true,
            visibleHotSpotBackgrounds: "always",
            autoScrollingMode: "onstart"
        });
    });
</script>

</body>
</html>