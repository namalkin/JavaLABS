<%@ page contentType="text/html;charset=UTF-8" %>
<%
    String now = (String) request.getAttribute("now");
    Object visits = request.getAttribute("visits");
    String role = (String) session.getAttribute("role");
    java.util.List<String> newsList = (java.util.List<String>) request.getAttribute("newsList");
%>
<html>
<head>
    <title>Новости игр</title>
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
        <h2>Новости игр</h2>
        <ul>
            <% if (newsList != null) {
                for (String n : newsList) { %>
                    <li><%= n %></li>
            <%  }
            } %>
        </ul>
    </div>
</body>
</html>
