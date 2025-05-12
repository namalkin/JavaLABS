<%@ page contentType="text/html;charset=UTF-8" %>
<%
    String now = (String) request.getAttribute("now");
    Object visits = request.getAttribute("visits");
    String role = (String) session.getAttribute("role");
    java.util.List<String> collectionsList = (java.util.List<String>) request.getAttribute("collectionsList");
%>
<html>
<head>
    <title>Подборки игр</title>
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
        <h2>Подборки</h2>
        <ul>
            <% if (collectionsList != null) {
                for (String c : collectionsList) { %>
                    <li><%= c %></li>
            <%  }
            } %>
        </ul>
    </div>
</body>
</html>
