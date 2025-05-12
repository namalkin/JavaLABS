<!-- filepath: d:\create\contain\JavaLABS\4\webapps\ROOT\main.jsp -->
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Игровой портал</title>
    <link rel="stylesheet" href="/myportal/css/style.css">
</head>
<body>
    <div class="info-bar">
        <span>Текущее время: <%= request.getAttribute("now") != null ? request.getAttribute("now") : "—" %></span>
        <span>Количество посещений: <%= request.getAttribute("visits") != null ? request.getAttribute("visits") : "—" %></span>
    </div>
    <div class="container">
        <h1>Добро пожаловать на игровой портал!</h1>
        <form method="post" action="login" class="login-form">
            <label>Имя пользователя:</label>
            <input type="text" name="username" required><br>
            <label>Пароль:</label>
            <input type="password" name="password" required><br>
            <input type="submit" value="Войти">
        </form>
        <div style="color:red;">
            <%= request.getAttribute("error") != null ? request.getAttribute("error") : "" %>
        </div>
    </div>
</body>
</html>