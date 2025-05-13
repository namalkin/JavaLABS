package servlet;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import service.VisitCounterService;


/**
 * Класс CollectionsServlet отображает страницу коллекций,
 * увеличивает счетчик посещений и передает данные времени и количества посещений в collections.jsp.
 * @WebServlet("/collections")
 */
public class CollectionsServlet extends HttpServlet {

    /**
     * Метод init() инициализирует объект VisitCounterService,
     * который используется для подсчета посещений.
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        VisitCounterService collectionsCounter = (VisitCounterService) getServletContext().getAttribute("visitCounterService_collections");
        if (collectionsCounter != null) {
            request.setAttribute("visits", collectionsCounter.incrementAndGet());
        }
        String now = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        request.setAttribute("now", now);

        content.PortalContent portalContent = (content.PortalContent) getServletContext().getAttribute("portalContent");
        if (portalContent != null) {
            request.setAttribute("collectionsList", portalContent.getCollections());
        }

        request.getRequestDispatcher("collections.jsp").forward(request, response);
    }
}
