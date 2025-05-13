
package servlet;

import service.VisitCounterService;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Класс MainPageServlet отображает главную страницу портала,
 * увеличивает счетчик посещений и передает данные времени и количества посещений в main.jsp.
 */
public class MainPageServlet extends HttpServlet {
    private VisitCounterService counterService;

    /**
     * Метод init() инициализирует объект VisitCounterService,
     */
    @Override
    public void init() {
        counterService = (VisitCounterService) getServletContext().getAttribute("visitCounterService");
    }

    /**
     * Метод doGet() обрабатывает GET-запросы, увеличивает счетчик посещений,
     * получает текущее время и передает данные в JSP-страницу.
     *
     * @param req  HttpServletRequest объект, содержащий запрос от клиента
     * @param resp HttpServletResponse объект, содержащий ответ для клиента
     * @throws ServletException если возникает ошибка при обработке запроса
     * @throws IOException      если возникает ошибка ввода-вывода
     */
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
