import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * Класс StaticResourceFilter фильтрует запросы к статическим ресурсам (css, js, изображения).
 */
@WebFilter("/*")
public class StaticResourceFilter implements Filter {
    
    /**
     * Метод doFilter() обрабатывает запросы к статическим ресурсам,
     * пропуская их через фильтр, и передает управление следующему элементу цепочки фильтров.
     * @param request  HttpServletRequest объект, содержащий запрос от клиента
     * @param response HttpServletResponse объект, содержащий ответ для клиента
     * @param chain    FilterChain объект, представляющий цепочку фильтров
     * @throws IOException      если возникает ошибка ввода-вывода
     * @throws ServletException если возникает ошибка при обработке запроса
     */
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        String uri = req.getRequestURI();
        if (uri.endsWith(".css") || uri.endsWith(".js") || uri.endsWith(".png") ||
            uri.endsWith(".jpg") || uri.endsWith(".jpeg") || uri.endsWith(".gif")) {
            // Пропустить статику
            chain.doFilter(request, response);
        } else {
            // Всё остальное — обычная обработка
            chain.doFilter(request, response);
        }
    }
}
