<!-- filepath: d:\create\contain\JavaLABS\4\webapps\ROOT\profile.jsp -->
<%@ page import="service.UserService" %>
<%@ page import="jakarta.servlet.ServletContext" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%
    String user = (String) session.getAttribute("user");
    String role = (String) session.getAttribute("role");
    String now = (String) request.getAttribute("now");
    Integer profileVisits = (Integer) request.getAttribute("profileVisits");
    Integer userProfileVisits = (Integer) request.getAttribute("userProfileVisits");
    if (user == null) {
        response.sendRedirect("index");
        return;
    }
%>
<html>
<head>
    <title>Профиль пользователя</title>
    <link rel="stylesheet" href="/myportal/css/style.css">
</head>
<body>
    <% if ("Администратор".equals(role) || "Модератор".equals(role)) { %>
    <div class="info-bar">
        <span>Текущее время: <%= now != null ? now : "—" %></span>
        <% if ("Администратор".equals(role)) { %>
            <span>Всего посещений профиля: <%= profileVisits != null ? profileVisits : "—" %></span>
        <% } else { %>
            <span>Ваших посещений профиля: <%= userProfileVisits != null ? userProfileVisits : "—" %></span>
        <% } %>
    </div>
    <% } %>
    <%@ include file="menu.jspf" %>
    <div class="container">

        <h1>Профиль пользователя: <%= user %></h1>
        <p>Ваша роль: <%= role %></p>
        <% if (avatar != null) { %>
        <img src="<%= avatar %>" alt="Аватар" style="max-width:200px;max-height:200px;"><br>
        <% } else { %>
        <p>Аватар не загружен.</p>
        <% } %>
        <form action="upload" method="post" enctype="multipart/form-data">
            <input type="file" name="image" accept="image/*" required>
            <input type="submit" value="Загрузить изображение">
        </form>
    </div>
</body>
</html>