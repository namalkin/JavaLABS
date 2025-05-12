import jakarta.servlet.*;
import jakarta.servlet.annotation.*;
import dao.VisitCounterDao;
import service.VisitCounterService;
import service.UserService;
import content.PortalContent;
import java.io.File;

/**
 * Класс AppContextListener инициализирует сервисы и объекты при запуске приложения,
 * включая счетчики посещений, пользователей и контент.
 */
@WebListener
public class AppContextListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        // Создать директорию для счетчиков, если не существует
        String countersDir = "d:/create/contain/JavaLABS/4/counters";
        File dir = new File(countersDir);
        if (!dir.exists()) dir.mkdirs();

        VisitCounterDao counterDao = new VisitCounterDao(countersDir + "/visit_counter.ser");
        VisitCounterService counterService = new VisitCounterService(counterDao);
        sce.getServletContext().setAttribute("visitCounterService", counterService);

        UserService userService = new UserService();
        sce.getServletContext().setAttribute("userService", userService);

        PortalContent portalContent = new PortalContent();
        sce.getServletContext().setAttribute("portalContent", portalContent);

        sce.getServletContext().setAttribute("visitCounterService_dashboard",
            new VisitCounterService(new VisitCounterDao(countersDir + "/visit_dashboard.ser")));
        sce.getServletContext().setAttribute("visitCounterService_news",
            new VisitCounterService(new VisitCounterDao(countersDir + "/visit_news.ser")));
        sce.getServletContext().setAttribute("visitCounterService_reviews",
            new VisitCounterService(new VisitCounterDao(countersDir + "/visit_reviews.ser")));
        sce.getServletContext().setAttribute("visitCounterService_collections",
            new VisitCounterService(new VisitCounterDao(countersDir + "/visit_collections.ser")));
        sce.getServletContext().setAttribute("visitCounterService_games",
            new VisitCounterService(new VisitCounterDao(countersDir + "/visit_games.ser")));
        sce.getServletContext().setAttribute("visitCounterService_profile",
            new VisitCounterService(new VisitCounterDao(countersDir + "/visit_profile.ser")));
    }
    @Override
    public void contextDestroyed(ServletContextEvent sce) {}
}
