package servlets;

import models.User;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;

public class LoginServlet extends HttpServlet {
    // Простой метод проверки: если имя пользователя содержит "admin" или "mod", то назначаем роль
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password"); // не используем валидацию, демонстрация

        // Простейшая проверка (в реальном приложении – база данных и шифрование)
        String role = "visitor";
        if (username != null) {
            if (username.toLowerCase().contains("admin")) {
                role = "admin";
            } else if (username.toLowerCase().contains("mod")) {
                role = "moderator";
            }
        }

        // Создаем объект пользователя и сохраняем в сессии
        User user = new User(username, role);
        HttpSession session = req.getSession();
        session.setAttribute("user", user);

        // Перенаправляем на личный кабинет
        resp.sendRedirect("userCabinet.jsp");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Просто переадресация на форму логина
        resp.sendRedirect("login.jsp");
    }
}
