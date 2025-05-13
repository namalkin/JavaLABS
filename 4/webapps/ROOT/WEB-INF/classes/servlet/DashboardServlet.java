package servlet;
import jakarta.servlet.*;
import jakarta.servlet.http.*;

import java.io.IOException;
import service.VisitCounterService;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import content.PortalContent;

/**
 * Класс DashboardServlet отображает личный кабинет пользователя,
 * собирает статистику посещений и передает основные данные на dashboard.jsp.
 * @WebServlet("/dashboard")
 */
public class DashboardServlet extends HttpServlet {

    /**
     * Метод doGet() обрабатывает GET-запросы, проверяет сессию пользователя,
     * собирает статистику посещений и передает данные на dashboard.jsp.
     *
     * @param request  HttpServletRequest объект, содержащий запрос от клиента
     * @param response HttpServletResponse объект, содержащий ответ для клиента
     * @throws ServletException если возникает ошибка при обработке запроса
     * @throws IOException      если возникает ошибка ввода-вывода
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(false);

        if (session != null && session.getAttribute("user") != null) {
            String role = (String) session.getAttribute("role");

            // Счетчик только для dashboard
            VisitCounterService dashboardCounter = (VisitCounterService) getServletContext().getAttribute("visitCounterService_dashboard");
            int dashboardVisits = dashboardCounter.incrementAndGet();
            request.setAttribute("visits", dashboardVisits);

            String now = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
            request.setAttribute("now", now);

            // Передаем все счетчики только админу и модератору
            if ("Администратор".equals(role) || "Модератор".equals(role)) {
                request.setAttribute("dashboardVisits", dashboardVisits);
                request.setAttribute("newsVisits", ((VisitCounterService) getServletContext().getAttribute("visitCounterService_news")).getCount());
                request.setAttribute("reviewsVisits", ((VisitCounterService) getServletContext().getAttribute("visitCounterService_reviews")).getCount());
                request.setAttribute("collectionsVisits", ((VisitCounterService) getServletContext().getAttribute("visitCounterService_collections")).getCount());
                request.setAttribute("gamesVisits", ((VisitCounterService) getServletContext().getAttribute("visitCounterService_games")).getCount());
                // Добавлено: общий счетчик посещений профиля
                request.setAttribute("profileVisits", ((VisitCounterService) getServletContext().getAttribute("visitCounterService_profile")).getCount());
            }

            PortalContent portalContent = (PortalContent) getServletContext().getAttribute("portalContent");
            request.setAttribute("bestGames", portalContent.getBestGames());
            request.setAttribute("news", portalContent.getNews());
            request.setAttribute("reviews", portalContent.getReviews());
            request.setAttribute("collections", portalContent.getCollections());

            request.getRequestDispatcher("dashboard.jsp").forward(request, response);
        } else {
            response.sendRedirect("index");
        }
    }
}
