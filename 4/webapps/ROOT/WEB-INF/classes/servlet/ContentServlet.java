package servlet;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import content.PortalContent;
import java.io.IOException;

/**
 * Класс ContentServlet позволяет модераторам добавлять новости, обзоры и подборки
 * через форму на странице content.jsp.
 */
public class ContentServlet extends HttpServlet {

    /**
     * Метод doGet() обрабатывает GET-запросы, проверяет роль пользователя,
     * и передает данные в JSP-страницу.
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
        if (!"Модератор".equals(role)) {
            resp.sendRedirect("dashboard");
            return;
        }
        req.getRequestDispatcher("content.jsp").forward(req, resp);
    }

    /**
     * Метод doPost() обрабатывает POST-запросы, проверяет роль пользователя,
     * добавляет новости, обзоры и подборки в PortalContent и перенаправляет на страницу content.
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
        if (!"Модератор".equals(role)) {
            resp.sendRedirect("dashboard");
            return;
        }
        content.PortalContent portalContent = (content.PortalContent) getServletContext().getAttribute("portalContent");
        if (portalContent == null) {
            req.setAttribute("error", "Ошибка: PortalContent не инициализирован.");
            req.getRequestDispatcher("content.jsp").forward(req, resp);
            return;
        }
        String type = req.getParameter("type");
        if ("news".equals(type)) {
            String title = req.getParameter("title");
            String text = req.getParameter("text");
            String author = (String) session.getAttribute("user");
            if (title != null && !title.trim().isEmpty() && text != null && !text.trim().isEmpty()) {
                portalContent.addNews(title, text, author);
            }
        } else {
            String text = req.getParameter("text");
            if (text != null && !text.trim().isEmpty()) {
                if ("review".equals(type)) {
                    portalContent.addReview(text);
                } else if ("collection".equals(type)) {
                    portalContent.addCollection(text);
                }
            }
        }
        resp.sendRedirect("content");
    }
}
