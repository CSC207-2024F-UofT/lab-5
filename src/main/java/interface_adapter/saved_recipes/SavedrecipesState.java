package interface_adapter.saved_recipes;

import java.util.Map;

import entity.Recipe;

/**
 * The state for the saved recipes View Model.
 */
public class SavedrecipesState {
    private String username;
    private Map<Recipe, Integer> recipes;

    public SavedrecipesState(SavedrecipesState copy) {
        this.username = copy.username;
        this.recipes = copy.recipes;
    }

    public SavedrecipesState() {

    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Map<Recipe, Integer> getRecipes() {
        return recipes;
    }

    public void setRecipes(Map<Recipe, Integer> recipes) {
        this.recipes = recipes;
    }
}
