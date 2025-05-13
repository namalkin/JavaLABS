<!-- filepath: d:\create\contain\JavaLABS\4\webapps\ROOT\dashboard.jsp -->
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="jakarta.servlet.http.HttpSession" %>
<%@ page import="java.util.List" %>
<%@ page import="content.PortalContent.NewsPost" %>
<%
    // session неявный объект
    String user = (String) session.getAttribute("user");
    String role = (String) session.getAttribute("role");
    String now = (String) request.getAttribute("now");
    Object visits = request.getAttribute("visits");
    List<String> bestGames = (List<String>) request.getAttribute("bestGames");
    List<NewsPost> news = (List<NewsPost>) request.getAttribute("news");
    List<String> reviews = (List<String>) request.getAttribute("reviews");
    List<String> collections = (List<String>) request.getAttribute("collections");
    Integer dashboardVisits = (Integer) request.getAttribute("dashboardVisits");
    Integer newsVisits = (Integer) request.getAttribute("newsVisits");
    Integer reviewsVisits = (Integer) request.getAttribute("reviewsVisits");
    Integer collectionsVisits = (Integer) request.getAttribute("collectionsVisits");
    Integer gamesVisits = (Integer) request.getAttribute("gamesVisits");
    Integer profileVisits = (Integer) request.getAttribute("profileVisits"); // заменили userProfileVisits
%>
<html>
<head>
    <title>Кабинет</title>
    <link rel="stylesheet" href="/myportal/css/style.css">
</head>
<body>
    <% if ("Администратор".equals(role) || "Модератор".equals(role)) { %>
    <div class="info-bar">
        <span>Текущее время: <%= now != null ? now : "—" %></span>
        <span>Посещений этой страницы: <%= visits != null ? visits : "—" %></span>
    </div>
    <% } %>
    <%@ include file="menu.jspf" %>
    <div class="dashboard-layout">
        <% if ("Администратор".equals(role) || "Модератор".equals(role)) { %>
        <aside class="dashboard-header-side">
            <b>Статистика посещений:</b>
            <ul>
                <li>Кабинет: <%= dashboardVisits != null ? dashboardVisits : "—" %></li>
                <li>Лучшие игры: <%= gamesVisits != null ? gamesVisits : "—" %></li>
                <li>Новости: <%= newsVisits != null ? newsVisits : "—" %></li>
                <li>Обзоры: <%= reviewsVisits != null ? reviewsVisits : "—" %></li>
                <li>Подборки: <%= collectionsVisits != null ? collectionsVisits : "—" %></li>
                <li>Профиль: <%= profileVisits != null ? profileVisits : "—" %></li>
            </ul>
        </aside>
        <% } %>
        <div class="container dashboard-main">
            <div class="dashboard-header">
                <div>
                    <h1 style="display:inline"><%= user %></h1>
                    <span style="color:#888; font-size:1em;">[<%= role %>]</span>
                </div>
            </div>
            <div class="dashboard-blocks">
                <div class="dashboard-card">
                    <h3>Лучшие игры</h3>
                    <ul>
                        <% for (String game : bestGames) { %>
                            <li><%= game %></li>
                        <% } %>
                    </ul>
                    <a href="games" class="more-link">Подробнее</a>
                </div>
                <div class="dashboard-card">
                    <h3>Новости игр</h3>
                    <ul>
                        <% for (NewsPost n : news) { %>
                            <li>
                                <b>
                                    <a href="/myportal/newsview?id=<%= n.getId() %>"><%= n.getTitle() %></a>
                                </b><br>
                                <span><%= n.getText() %></span><br>
                                <small>Автор: <%= n.getAuthor() %> | Просмотров: <%= n.getViews() %></small>
                            </li>
                        <% } %>
                    </ul>
                    <a href="news" class="more-link">Все новости</a>
                </div>
                <div class="dashboard-card">
                    <h3>Обзоры</h3>
                    <ul>
                        <% for (String r : reviews) { %>
                            <li><%= r %></li>
                        <% } %>
                    </ul>
                    <a href="reviews" class="more-link">Читать обзоры</a>
                </div>
                <div class="dashboard-card">
                    <h3>Подборки</h3>
                    <ul>
                        <% for (String c : collections) { %>
                            <li><%= c %></li>
                        <% } %>
                    </ul>
                    <a href="collections" class="more-link">Смотреть подборки</a>
                </div>
            </div>
        </div>
    </div>
</body>
</html>