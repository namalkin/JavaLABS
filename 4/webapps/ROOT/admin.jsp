<%@ page import="java.util.Map" %>
<%@ page import="domain.User" %>
<%@ page import="domain.Role" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%
    Map<String, User> users = (Map<String, User>) request.getAttribute("users");
    String role = (String) session.getAttribute("role");
%>
<html>
<head>
    <title>Управление пользователями</title>
    <link rel="stylesheet" href="/myportal/css/style.css">
</head>
<body>
    <%@ include file="menu.jspf" %>
    <div class="container">
        <h2>Пользователи</h2>
        <table border="1" cellpadding="5">
            <tr>
                <th>Имя</th>
                <th>Роль</th>
                <th>Изменить роль</th>
            </tr>
            <% for (User u : users.values()) { %>
            <tr>
                <td><%= u.getUsername() %></td>
                <td><%= u.getRole() %></td>
                <td>
                    <form method="post" style="display:inline;">
                        <input type="hidden" name="action" value="changerole">
                        <input type="hidden" name="username" value="<%= u.getUsername() %>">
                        <select name="newrole">
                            <% for (Role r : Role.values()) { %>
                                <option value="<%= r.name() %>" <%= u.getRole() == r ? "selected" : "" %>><%= r.name() %></option>
                            <% } %>
                        </select>
                        <input type="submit" value="Сменить">
                    </form>
                </td>
            </tr>
            <% } %>
        </table>
        <h3>Добавить пользователя</h3>
        <form method="post">
            <input type="hidden" name="action" value="add">
            <input type="text" name="username" placeholder="Имя" required>
            <input type="password" name="password" placeholder="Пароль" required>
            <select name="newrole">
                <% for (Role r : Role.values()) { %>
                    <option value="<%= r.name() %>"><%= r.name() %></option>
                <% } %>
            </select>
            <input type="submit" value="Добавить">
        </form>
    </div>
</body>
</html>
