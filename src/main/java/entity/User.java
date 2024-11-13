package entity;

import java.util.ArrayList;
import java.util.List;

public class User {
    private String username;
    private String password;
    private List<Recipe> bookmarks;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
        this.bookmarks = new ArrayList<>();
    }

    // Getters and Setters
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

    public List<Recipe> getBookmarks() {
        return bookmarks;
    }

    /*
    Add a recipe to the list of bookmarks.
     */
    public void addBookmark(Recipe recipe) {
        this.bookmarks.add(recipe);
    }
}
