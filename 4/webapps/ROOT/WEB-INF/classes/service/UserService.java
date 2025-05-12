package service;

import domain.User;
import domain.Role;
import java.util.*;

/**
 * Класс UserService управляет пользователями портала:
 * аутентификация, добавление, изменение ролей, получение аватара и списка пользователей.
 */
public class UserService {
    private final Map<String, User> users = new HashMap<>();

    /**
     * Конструктор UserService. Добавляет тестовых пользователей.
     */
    public UserService() {
        users.put("admin", new User("admin", "1234", Role.ADMIN));
        users.put("moderator", new User("moderator", "abcd", Role.MODERATOR));
        users.put("user", new User("user", "pass", Role.USER));
    }

    /**
     * Аутентификация пользователя по логину и паролю.
     * @param username имя пользователя
     * @param password пароль
     * @return объект User или null, если не найден
     */
    public User authenticate(String username, String password) {
        User user = users.get(username);
        if (user != null && user.getPassword().equals(password)) return user;
        return null;
    }

    /**
     * Получить пользователя по имени.
     * @param username имя пользователя
     * @return объект User или null
     */
    public User getUser(String username) {
        return users.get(username);
    }

    /**
     * Получить путь к аватару пользователя.
     * @param username имя пользователя
     * @return путь к изображению или null
     */
    public String getAvatar(String username) {
        User user = users.get(username);
        return user != null ? user.getImagePath() : null;
    }

    /**
     * Добавить нового пользователя.
     * @param username имя пользователя
     * @param password пароль
     * @param role роль
     * @return true, если добавлен, false если уже существует
     */
    public boolean addUser(String username, String password, Role role) {
        if (users.containsKey(username)) return false;
        users.put(username, new User(username, password, role));
        return true;
    }

    /**
     * Изменить роль пользователя.
     * @param username имя пользователя
     * @param role новая роль
     * @return true, если роль изменена, false если пользователь не найден
     */
    public boolean setUserRole(String username, Role role) {
        User user = users.get(username);
        if (user == null) return false;
        user.setRole(role);
        return true;
    }

    /**
     * Получить всех пользователей.
     * @return неизменяемая Map пользователей
     */
    public Map<String, User> getAllUsers() {
        return Collections.unmodifiableMap(users);
    }
}
