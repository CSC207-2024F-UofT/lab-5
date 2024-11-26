package entity;

import java.util.ArrayList;
import java.util.List;

public class User {
    private String username;
    private String password;
    private List<Recipe> bookmarks;
    private List<Recipe> recentlyViewed;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
        this.bookmarks = List.of();
        this.recentlyViewed = List.of();
    }

    // Overloaded constructor for user with bookmarks and recentlyViewed
    public User(String username, String password, List<Recipe> bookmarks, List<Recipe> recentlyViewed) {
        this.username = username;
        this.password = password;
        this.bookmarks = bookmarks;
        this.recentlyViewed = recentlyViewed;
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

    public List<Recipe> getRecentlyViewed() {
        return recentlyViewed;
    }

    /*
        Add a recipe to the list of bookmarks.
         */
    public void addBookmark(Recipe recipe) {
        this.bookmarks.add(recipe);
    }

    /*
    Update recentlyViewed when the user views a recipe.
     */
    public void addRecentlyViewed(Recipe recipe) {
        this.recentlyViewed.add(recipe);
    }
}
