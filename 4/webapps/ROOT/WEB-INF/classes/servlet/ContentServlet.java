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

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        String role = (String) session.getAttribute("role");
        if (!"Модератор".equals(role)) {
            resp.sendRedirect("dashboard");
            return;
        }
        PortalContent portalContent = (PortalContent) getServletContext().getAttribute("portalContent");
        if (portalContent == null) {
            req.setAttribute("error", "Ошибка: PortalContent не инициализирован.");
            req.getRequestDispatcher("content.jsp").forward(req, resp);
            return;
        }
        String type = req.getParameter("type");
        String text = req.getParameter("text");
        if (text != null && !text.trim().isEmpty()) {
            if ("news".equals(type)) {
                portalContent.addNews(text);
            } else if ("review".equals(type)) {
                portalContent.addReview(text);
            } else if ("collection".equals(type)) {
                portalContent.addCollection(text);
            }
        }
        resp.sendRedirect("content");
    }
}
