package servlet;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import service.VisitCounterService;

/**
 * Класс NewsServlet отображает страницу новостей,
 * увеличивает счетчик посещений и передает список новостей в news.jsp.
 * @WebServlet("/news")
 */
public class NewsServlet extends HttpServlet {

    /**
     * Метод doGet() обрабатывает GET-запросы, увеличивает счетчик посещений,
     * получает текущее время и передает данные в JSP-страницу.
     *
     * @param request  HttpServletRequest объект, содержащий запрос от клиента
     * @param response HttpServletResponse объект, содержащий ответ для клиента
     * @throws ServletException если возникает ошибка при обработке запроса
     * @throws IOException      если возникает ошибка ввода-вывода
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        VisitCounterService newsCounter = (VisitCounterService) getServletContext().getAttribute("visitCounterService_news");
        if (newsCounter != null) {
            request.setAttribute("visits", newsCounter.incrementAndGet());
        }
        String now = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        request.setAttribute("now", now);

        content.PortalContent portalContent = (content.PortalContent) getServletContext().getAttribute("portalContent");
        if (portalContent != null) {
            // Инкремент просмотров для каждой новости
            for (content.PortalContent.NewsPost post : portalContent.getNews()) {
                post.incrementViews();
            }
            request.setAttribute("newsList", portalContent.getNews());
        }

        request.getRequestDispatcher("news.jsp").forward(request, response);
    }
}
