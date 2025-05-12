package servlet;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import service.VisitCounterService;

/**
 * Класс ProfileServlet отображает страницу профиля пользователя,
 * ведет учет посещений профиля и передает соответствующие данные в profile.jsp.
 */
// @WebServlet("/profile")
public class ProfileServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        VisitCounterService profileCounter = (VisitCounterService) getServletContext().getAttribute("visitCounterService_profile");
        int totalProfileVisits = 0;
        if (profileCounter != null) {
            totalProfileVisits = profileCounter.incrementAndGet();
        }
        String now = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        request.setAttribute("now", now);

        HttpSession session = request.getSession(false);
        String role = null;
        if (session != null) {
            role = (String) session.getAttribute("role");
            // Индивидуальный счетчик посещений профиля для пользователя
            Integer userProfileVisits = (Integer) session.getAttribute("userProfileVisits");
            if (userProfileVisits == null) userProfileVisits = 0;
            userProfileVisits++;
            session.setAttribute("userProfileVisits", userProfileVisits);

            // Для админа/модератора показываем общее число посещений
            if ("Администратор".equals(role) || "Модератор".equals(role)) {
                request.setAttribute("profileVisits", totalProfileVisits);
            } else {
                request.setAttribute("userProfileVisits", userProfileVisits);
            }
        }
        request.getRequestDispatcher("profile.jsp").forward(request, response);
    }
}
