<%@ page session="true" contentType="text/html; charset=UTF-8" %>
<%
    com.gameportal.models.User user = (com.gameportal.models.User) session.getAttribute("user");
    if(user == null){
        response.sendRedirect("login.jsp");
        return;
    }
%>
<html>
<head>
    <title>Личный кабинет</title>
</head>
<body>
    <h2>Привет, <%= user.getUsername() %>!</h2>
    <p>Твоя роль: <%= user.getRole() %></p>
    <p><a href="portal">Вернуться на портал</a></p>
    <p><a href="upload.jsp">Загрузить изображение</a></p>
</body>
</html>
