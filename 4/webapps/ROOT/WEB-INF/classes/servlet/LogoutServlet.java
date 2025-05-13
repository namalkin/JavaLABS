package servlet;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.io.IOException;

/**
 * Класс LogoutServlet отвечает за выход пользователя из системы,
 * завершает сессию и перенаправляет на главную страницу.
 */
public class LogoutServlet extends HttpServlet {

    /**
     * Метод doGet() обрабатывает GET-запросы, завершает сессию пользователя
     * и перенаправляет на главную страницу.
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
        if (session != null) {
            session.invalidate();
        }
        response.sendRedirect("index");
    }
}
