package models;

public class User {
    private String username;
    private String role; // "admin", "moderator", "visitor"

    public User(String username, String role) {
        this.username = username;
        this.role = role;
    }

    // Геттеры и сеттеры
    public String getUsername() {
        return username;
    }

    public String getRole() {
        return role;
    }
}
