package service;

import domain.User;

import jakarta.servlet.http.HttpSession;

/**
 * Класс AuthService реализует методы для входа, выхода и получения текущего пользователя из сессии.
 */
public class AuthService {
    /**
     * Войти в систему: сохранить пользователя в сессию.
     * @param session HttpSession
     * @param user объект User
     */
    public void login(HttpSession session, User user) {
        session.setAttribute("user", user);
    }

    /**
     * Выйти из системы: завершить сессию.
     * @param session HttpSession
     */
    public void logout(HttpSession session) {
        session.invalidate();
    }

    /**
     * Получить текущего пользователя из сессии.
     * @param session HttpSession
     * @return объект User или null
     */
    public User getCurrentUser(HttpSession session) {
        return (User) session.getAttribute("user");
    }
}
