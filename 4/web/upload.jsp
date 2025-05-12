<%@ page contentType="text/html; charset=UTF-8" %>
<html>
<head>
    <title>Загрузка изображения</title>
</head>
<body>
    <h2>Форма загрузки изображения</h2>
    <!-- Пример простой формы (без реальной обработки multipart-запроса) -->
    <form action="upload" method="post">
        Имя файла: <input type="text" name="fileName" /><br/>
        <input type="submit" value="Загрузить" />
    </form>
    <p><a href="userCabinet.jsp">Назад в кабинет</a></p>
</body>
</html>
