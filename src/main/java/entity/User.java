package entity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class User {
    private String username;
    private String password;
    private final List<Recipe> bookmarks;
    private final List<Recipe> recentlyViewed;
    private final Map<String, List<Recipe>> folders;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
        this.bookmarks = List.of();
        this.recentlyViewed = List.of();
        this.folders = new HashMap<>();
    }

    // Overloaded constructor for user with bookmarks and recentlyViewed
    public User(String username, String password, List<Recipe> bookmarks, List<Recipe> recentlyViewed) {
        this.username = username;
        this.password = password;
        this.bookmarks = bookmarks;
        this.recentlyViewed = recentlyViewed;
        this.folders = new HashMap<>();
    }

    // Overloaded constructor for user with bookmarks, recentlyViewed and folders
    public User(String username, String password, List<Recipe> bookmarks, List<Recipe> recentlyViewed,
                Map<String, List<Recipe>> folders) {
        this.username = username;
        this.password = password;
        this.bookmarks = bookmarks;
        this.recentlyViewed = recentlyViewed;
        this.folders = folders;
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

    public Map<String, List<Recipe>> getFolders() {
        return folders;
    }

    public List<Recipe> getFolder(String folder) {
        if (!folders.containsKey(folder)) {
            System.out.println("No such folder: " + folder);
            return List.of();
        }
        else {
            return folders.get(folder);
        }
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

    public void clearRecentlyViewed() {
        this.recentlyViewed.clear();
    }

    public void addFolder(String folder) {
        this.folders.put(folder, new ArrayList<>());
    }

    public void addRecipeToFolder(String folder, Recipe recipe) {
        if (!this.folders.containsKey(folder)) {
            addFolder(folder);
        }
        this.folders.get(folder).add(recipe);
    }
}
