<%@ page contentType="text/html;charset=UTF-8" %>
<%
    String role = (String) session.getAttribute("role");
    String error = (String) request.getAttribute("error");
    content.PortalContent portalContent = (content.PortalContent) application.getAttribute("portalContent");
%>
<html>
<head>
    <title>Добавить контент</title>
    <link rel="stylesheet" href="/myportal/css/style.css">
</head>
<body>
    <%@ include file="menu.jspf" %>
    <div class="container">
        <h2>Добавить контент</h2>
        <% if (error != null) { %>
            <div style="color:red;"><%= error %></div>
        <% } %>
        <form method="post">
            <label>Тип:</label>
            <select name="type">
                <option value="news">Новость</option>
                <option value="review">Обзор</option>
                <option value="collection">Подборка</option>
            </select>
            <br>
            <textarea name="text" rows="3" cols="50" placeholder="Текст..." required></textarea>
            <br>
            <input type="submit" value="Добавить">
        </form>
        <hr>
        <h3>Последние новости</h3>
        <ul>
            <% if (portalContent != null) {
                for (String n : portalContent.getNews()) { %>
                    <li><%= n %></li>
            <%  }
            } %>
        </ul>
        <h3>Последние обзоры</h3>
        <ul>
            <% if (portalContent != null) {
                for (String r : portalContent.getReviews()) { %>
                    <li><%= r %></li>
            <%  }
            } %>
        </ul>
        <h3>Последние подборки</h3>
        <ul>
            <% if (portalContent != null) {
                for (String c : portalContent.getCollections()) { %>
                    <li><%= c %></li>
            <%  }
            } %>
        </ul>
    </div>
</body>
</html>
