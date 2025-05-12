<!-- filepath: d:\create\contain\JavaLABS\4\webapps\ROOT\upload.jsp -->
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head><title>Загрузка изображения</title></head>
<body>
    <h2>Загрузить изображение</h2>
    <form action="upload" method="post" enctype="multipart/form-data">
        <input type="file" name="image" accept="image/*" required>
        <input type="submit" value="Загрузить">
    </form>
</body>
</html>