package servlet;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import service.VisitCounterService;

/**
 * Класс ReviewsServlet отображает страницу обзоров,
 * увеличивает счетчик посещений и передает список обзоров в reviews.jsp.
 */
// @WebServlet("/reviews")
public class ReviewsServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        VisitCounterService reviewsCounter = (VisitCounterService) getServletContext().getAttribute("visitCounterService_reviews");
        if (reviewsCounter != null) {
            request.setAttribute("visits", reviewsCounter.incrementAndGet());
        }
        String now = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        request.setAttribute("now", now);

        content.PortalContent portalContent = (content.PortalContent) getServletContext().getAttribute("portalContent");
        if (portalContent != null) {
            request.setAttribute("reviewsList", portalContent.getReviews());
        }

        request.getRequestDispatcher("reviews.jsp").forward(request, response);
    }
}
