package servlet;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import service.UserService;
import domain.Role;
import java.io.IOException;

/**
 * Класс AdminServlet предоставляет интерфейс для управления пользователями:
 * просмотр, добавление и изменение ролей пользователей.
 */
public class AdminServlet extends HttpServlet {

    /**
     * Метод doGet() обрабатывает GET-запросы, проверяет роль пользователя,
     * получает список пользователей и передает его в JSP-страницу.
     *
     * @param req  HttpServletRequest объект, содержащий запрос от клиента
     * @param resp HttpServletResponse объект, содержащий ответ для клиента
     * @throws ServletException если возникает ошибка при обработке запроса
     * @throws IOException      если возникает ошибка ввода-вывода
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        String role = (String) session.getAttribute("role");
        if (!"Администратор".equals(role)) {
            resp.sendRedirect("dashboard");
            return;
        }
        UserService userService = (UserService) getServletContext().getAttribute("userService");
        req.setAttribute("users", userService.getAllUsers());
        req.getRequestDispatcher("admin.jsp").forward(req, resp);
    }

    /**
     * Метод doPost() обрабатывает POST-запросы, проверяет роль пользователя,
     * выполняет действия по добавлению пользователя или изменению его роли.
     *
     * @param req  HttpServletRequest объект, содержащий запрос от клиента
     * @param resp HttpServletResponse объект, содержащий ответ для клиента
     * @throws ServletException если возникает ошибка при обработке запроса
     * @throws IOException      если возникает ошибка ввода-вывода
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        String role = (String) session.getAttribute("role");
        if (!"Администратор".equals(role)) {
            resp.sendRedirect("dashboard");
            return;
        }
        UserService userService = (UserService) getServletContext().getAttribute("userService");
        String action = req.getParameter("action");
        String username = req.getParameter("username");
        if ("add".equals(action)) {
            String password = req.getParameter("password");
            String roleStr = req.getParameter("newrole");
            Role newRole = Role.valueOf(roleStr);
            userService.addUser(username, password, newRole);
        } else if ("changerole".equals(action)) {
            String roleStr = req.getParameter("newrole");
            Role newRole = Role.valueOf(roleStr);
            userService.setUserRole(username, newRole);
        }
        resp.sendRedirect("admin");
    }
}
