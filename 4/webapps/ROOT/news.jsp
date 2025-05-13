<%@ page contentType="text/html;charset=UTF-8" %>
<%
    String now = (String) request.getAttribute("now");
    Object visits = request.getAttribute("visits");
    String role = (String) session.getAttribute("role");
    java.util.List<content.PortalContent.NewsPost> newsList = (java.util.List<content.PortalContent.NewsPost>) request.getAttribute("newsList");
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
                for (content.PortalContent.NewsPost n : newsList) { %>
                    <li>
                        <b>
                            <a href="/myportal/newsview?id=<%= n.getId() %>"><%= n.getTitle() %></a>
                        </b><br>
                        <span><%= n.getText() %></span><br>
                        <small>Автор: <%= n.getAuthor() %> | Просмотров: <%= n.getViews() %></small>
                    </li>
            <%  }
            } %>
        </ul>
    </div>
</body>
</html>
