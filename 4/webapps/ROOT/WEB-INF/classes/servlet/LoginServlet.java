package servlet;
import jakarta.servlet.*;
import jakarta.servlet.http.*;

import java.io.IOException;
import service.UserService;
import domain.User; // Добавьте эту строку

/**
 * Класс LoginServlet обрабатывает вход пользователя,
 * устанавливает сессионные атрибуты и перенаправляет на dashboard или main.jsp.
 * @WebServlet("/login")
 */
public class LoginServlet extends HttpServlet {

    /**
     * Метод doGet() обрабатывает GET-запросы и перенаправляет на страницу входа.
     *
     * @param request  HttpServletRequest объект, содержащий запрос от клиента
     * @param response HttpServletResponse объект, содержащий ответ для клиента
     * @throws ServletException если возникает ошибка при обработке запроса
     * @throws IOException      если возникает ошибка ввода-вывода
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        // Используем UserService из контекста
        UserService userService = (UserService) getServletContext().getAttribute("userService");
        User userObj = null;
        if (userService != null && (userObj = userService.authenticate(username, password)) != null) {
            HttpSession session = request.getSession();
            session.setAttribute("user", username);
            // Получаем реальную роль пользователя
            String role;
            if (userObj.getRole() == domain.Role.ADMIN) {
                role = "Администратор";
            } else if (userObj.getRole() == domain.Role.MODERATOR) {
                role = "Модератор";
            } else {
                role = "Пользователь";
            }
            session.setAttribute("role", role);

            // Сохраняем аватар в сессию, если есть
            String avatar = userService.getAvatar(username);
            if (avatar != null) {
                session.setAttribute("avatar", avatar);
            }

            response.sendRedirect("dashboard");
        } else {
            request.setAttribute("error", "Неверные учетные данные!");
            // Передаём время и посещения для main.jsp
            service.VisitCounterService counterService = (service.VisitCounterService) getServletContext().getAttribute("visitCounterService");
            if (counterService != null) {
                request.setAttribute("visits", counterService.getCount());
            }
            String now = java.time.LocalDateTime.now().format(java.time.format.DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
            request.setAttribute("now", now);

            request.getRequestDispatcher("main.jsp").forward(request, response);
        }
    }
}
