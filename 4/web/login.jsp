<%@ page contentType="text/html; charset=UTF-8" %>
<html>
<head>
    <title>Вход в систему</title>
</head>
<body>
    <h2>Форма входа</h2>
    <form action="login" method="post">
        Логин: <input type="text" name="username" /><br/>
        Пароль: <input type="password" name="password" /><br/>
        <input type="submit" value="Войти" />
    </form>
</body>
</html>
