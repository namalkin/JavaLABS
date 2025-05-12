package servlets;

import utils.Counter;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

public class PortalServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Увеличиваем счетчик посещений
        int currentCount = Counter.increment();

        resp.setContentType("text/html; charset=UTF-8");
        PrintWriter out = resp.getWriter();

        // Текущая дата и время
        String currentTime = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss").format(new Date());

        out.println("<html><head><meta charset='UTF-8'><title>Игровой портал</title></head><body>");
        out.println("<h2>Добро пожаловать на игровой портал!</h2>");
        out.println("<p>Счетчик посещений: " + currentCount + "</p>");
        out.println("<p>Текущее время: " + currentTime + "</p>");
        out.println("<p><a href='login.jsp'>Войти в систему</a> | <a href='upload.jsp'>Загрузить изображение</a></p>");
        out.println("</body></html>");
    }
}
