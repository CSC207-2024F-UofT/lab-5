package interface_adapter;

import entity.Recipe;
import entity.User;

import java.util.List;

/**
 * The State information representing the recipe list.
 */
public class RecipeListState {
    private User user;
    private String folder;
    private List<Recipe> recipeList;

    public RecipeListState() {
        // TODO change this
        this.user = null;
        this.folder = null;
        this.recipeList = null;
    }

    public User getUser() {
        return this.user;
    }

    public String getFolder() {
        return this.folder;
    }

    public List<Recipe> getRecipeList() {
        return this.recipeList;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setFolder(String folder) {
        this.folder = folder;
    }

    public void setRecipeList(List<Recipe> recipeList) {
        this.recipeList = recipeList;
    }
}
