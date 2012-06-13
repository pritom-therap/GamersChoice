<%--
  Created by IntelliJ IDEA.
  User: pritom
  Date: 6/12/12
  Time: 7:12 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="/jmesa" prefix="jmesa" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head><title>Registered Users</title></head>
<body>
<div class="post">
    <div class="title">Registered members<%--${game.gameName} khelechen ?--%>
    </div>

    <div>
        <form name="UserListForm">


        <jmesa:tableModel id="tag" items="${users}" var="user">

            <c:url value="/User.htm" var="displayURL">
                <c:param name="userId" value="${user.userId}"/>
            </c:url>

            <jmesa:htmlTable width="800px" styleClass="jmesa" cellpadding="15px" cellspacing="5px">
                <jmesa:htmlRow>
                    <jmesa:htmlColumn property="userName">
                        <a href="${displayURL}">${user.userName}</a>
                    </jmesa:htmlColumn>

                    <jmesa:htmlColumn property="email"/>

                    <jmesa:htmlColumn property="playedGameCount" title="Games played"/>

                </jmesa:htmlRow>
            </jmesa:htmlTable>
        </jmesa:tableModel>
    </form>
    </div>
</div>
</body>
</html>