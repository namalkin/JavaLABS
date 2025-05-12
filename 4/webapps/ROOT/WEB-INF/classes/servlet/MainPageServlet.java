/**
 * Класс MainPageServlet отображает главную страницу портала,
 * увеличивает счетчик посещений и передает данные времени и количества посещений в main.jsp.
 */
package servlet;

import service.VisitCounterService;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class MainPageServlet extends HttpServlet {
    private VisitCounterService counterService;

    @Override
    public void init() {
        counterService = (VisitCounterService) getServletContext().getAttribute("visitCounterService");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        int visits = counterService.incrementAndGet();
        String now = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        req.setAttribute("visits", visits);
        req.setAttribute("now", now);
        req.getRequestDispatcher("main.jsp").forward(req, resp);
    }
}
