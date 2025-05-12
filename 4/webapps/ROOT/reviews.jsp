<%@ page contentType="text/html;charset=UTF-8" %>
<%
    String now = (String) request.getAttribute("now");
    Object visits = request.getAttribute("visits");
    String role = (String) session.getAttribute("role");
    java.util.List<String> reviewsList = (java.util.List<String>) request.getAttribute("reviewsList");
%>
<html>
<head>
    <title>Обзоры на игры</title>
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
        <h2>Обзоры на игры</h2>
        <ul>
            <% if (reviewsList != null) {
                for (String r : reviewsList) { %>
                    <li><%= r %></li>
            <%  }
            } %>
        </ul>
    </div>
</body>
</html>
