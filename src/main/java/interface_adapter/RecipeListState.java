package interface_adapter;

import entity.User;

/**
 * The State information representing the recipe list.
 */
public class RecipeListState {
    private User user;
    private String folder;

    public RecipeListState() {
        // TODO change this
        this.user = null;
        this.folder = null;
    }

    public User getUser() {
        return this.user;
    }

    public String getFolder() {
        return this.folder;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setFolder(String folder) {
        this.folder = folder;
    }
}
