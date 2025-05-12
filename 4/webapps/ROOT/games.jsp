<!-- filepath: d:\create\contain\JavaLABS\4\webapps\ROOT\games.jsp -->
<%@ page contentType="text/html;charset=UTF-8" %>
<%
    String now = (String) request.getAttribute("now");
    Object visits = request.getAttribute("visits");
    String role = (String) session.getAttribute("role");
    String search = request.getParameter("search");
    String[] games = {"Counter-Strike", "Dota 2", "Minecraft", "World of Warcraft", "League of Legends", "GTA V"};
%>
<html>
<head>
    <title>Игры</title>
    <link rel="stylesheet" href="/myportal/css/style.css">
</head>
<body>
    <% if ("Администратор".equals(role) || "Модератор".equals(role)) { %>
    <div class="info-bar">
        <span>Текущее время: <%= now != null ? now : "—" %></span>
        <span>Количество посещений: <%= visits != null ? visits : "—" %></span>
    </div>
    <% } %>
    <%@ include file="menu.jspf" %>
    <div class="container">
        <h2>Список игр</h2>
        <form method="get" action="games.jsp">
            <input type="text" name="search" placeholder="Поиск игр..." value="<%= search != null ? search : "" %>">
            <input type="submit" value="Найти">
        </form>
        <ul>
        <% for (String game : games) {
            if (search == null || game.toLowerCase().contains(search.toLowerCase())) { %>
                <li><%= game %></li>
        <%  }
        } %>
        </ul>
    </div>
</body>
</html>