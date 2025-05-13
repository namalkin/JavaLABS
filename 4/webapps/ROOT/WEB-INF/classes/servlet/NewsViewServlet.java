package servlet;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.io.IOException;
import content.PortalContent;

/**
 * Класс NewsViewServlet отображает страницу просмотра новости,
 * увеличивает счетчик просмотров и передает данные на newsview.jsp.
 * @WebServlet("/newsview")
 */
public class NewsViewServlet extends HttpServlet {

    /**
     * Метод doGet() обрабатывает GET-запросы, получает id новости,
     * увеличивает счетчик просмотров и передает данные на newsview.jsp.
     *
     * @param request  HttpServletRequest объект, содержащий запрос от клиента
     * @param response HttpServletResponse объект, содержащий ответ для клиента
     * @throws ServletException если возникает ошибка при обработке запроса
     * @throws IOException      если возникает ошибка ввода-вывода
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String idStr = request.getParameter("id");
        PortalContent portalContent = (PortalContent) getServletContext().getAttribute("portalContent");
        PortalContent.NewsPost post = null;
        if (idStr != null && portalContent != null) {
            try {
                int id = Integer.parseInt(idStr);
                post = portalContent.getNewsById(id);
                if (post != null) {
                    post.incrementViews();
                }
            } catch (NumberFormatException ignored) {}
        }
        if (post == null) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND, "Новость не найдена");
            return;
        }
        request.setAttribute("newsPost", post);
        request.getRequestDispatcher("newsview.jsp").forward(request, response);
    }
}
