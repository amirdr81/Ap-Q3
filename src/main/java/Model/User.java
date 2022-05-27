package Model;

import java.awt.*;

public class User {
    private String username;
    private String password;
    private int score = 0;
    private String avatar;
    public User(String username, String password, String avatar)
    {
        this.username = username;
        this.password = password;
        this.avatar = avatar;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public int getScore() {
        return score;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }
}
