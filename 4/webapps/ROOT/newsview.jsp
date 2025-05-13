<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="content.PortalContent.NewsPost" %>
<%
    NewsPost news = (NewsPost) request.getAttribute("newsPost");
    String role = (String) session.getAttribute("role");
%>
<html>
<head>
    <title><%= news != null ? news.getTitle() : "Новость не найдена" %></title>
    <link rel="stylesheet" href="/myportal/css/style.css">
</head>
<body>
    <%@ include file="menu.jspf" %>
    <div class="container">
        <% if (news != null) { %>
            <h2><%= news.getTitle() %></h2>
            <p><%= news.getText() %></p>
            <small>Автор: <%= news.getAuthor() %> | Просмотров: <%= news.getViews() %></small>
        <% } else { %>
            <p>Новость не найдена.</p>
        <% } %>
    </div>
</body>
</html>
