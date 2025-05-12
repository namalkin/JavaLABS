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
 */
// @WebServlet("/news")
public class NewsServlet extends HttpServlet {
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
            request.setAttribute("newsList", portalContent.getNews());
        }

        request.getRequestDispatcher("news.jsp").forward(request, response);
    }
}
