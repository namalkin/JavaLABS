package servlet;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import service.VisitCounterService;

/**
 * Класс GamesServlet отображает страницу лучших игр,
 * увеличивает счетчик посещений и передает данные времени и количества посещений в games.jsp.
 * @WebServlet("/games")
 */
public class GamesServlet extends HttpServlet {

    /**
     * Метод init() инициализирует объект VisitCounterService,
     * который используется для подсчета посещений.
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        VisitCounterService gamesCounter = (VisitCounterService) getServletContext().getAttribute("visitCounterService_games");
        if (gamesCounter != null) {
            request.setAttribute("visits", gamesCounter.incrementAndGet());
        }
        String now = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        request.setAttribute("now", now);
        request.getRequestDispatcher("games.jsp").forward(request, response);
    }
}
