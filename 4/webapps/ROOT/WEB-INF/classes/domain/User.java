package domain;

/**
 * Класс User представляет пользователя портала с логином, паролем, ролью и путем к аватару.
 */
public class User {
    private String username;
    private String password;
    private Role role;
    private String imagePath;

    public User(String username, String password, Role role) {
        this.username = username;
        this.password = password;
        this.role = role;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    /**
     * Переопределенный метод toString для представления пользователя в виде строки.
     *
     * @return Строковое представление пользователя.
     */
    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", role=" + role +
                ", imagePath='" + imagePath + '\'' +
                '}';
    }
}
