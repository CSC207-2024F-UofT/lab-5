package interface_adapter.saved_recipes;

import entity.Recipe;

import java.util.Map;

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

    public void addRecipe(Recipe recipe, Integer amount) {
        this.recipes.put(recipe, amount);
    }
}
