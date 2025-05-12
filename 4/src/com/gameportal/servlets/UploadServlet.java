package servlets;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;

public class UploadServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Здесь должна быть обработка multipart-запроса
        // Для простоты – только вывод информации
        String fileName = req.getParameter("fileName"); // эмуляция получения имени файла

        resp.setContentType("text/html; charset=UTF-8");
        PrintWriter out = resp.getWriter();
        out.println("<html><head><meta charset='UTF-8'><title>Загрузка изображения</title></head><body>");
        out.println("<h2>Файл " + (fileName != null ? fileName : "не указан") + " загружен (демо)!</h2>");
        out.println("<p><a href='index.jsp'>На главную</a></p>");
        out.println("</body></html>");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Переадресация на страницу загрузки
        resp.sendRedirect("upload.jsp");
    }
}
