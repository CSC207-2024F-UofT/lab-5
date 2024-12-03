package interface_adapter.recipe_details;

import entity.Recipe;
import entity.User;

/**
 * The state for the Recipe Details View Model.
 */
public class RecipeDetailsState {
    // private int rating;
    private Recipe recipe;
    private String username;

    public RecipeDetailsState(RecipeDetailsState copy) {
        this.recipe = copy.recipe;
        this.username = copy.username;
    }

    public RecipeDetailsState() {

    }

    public Recipe getRecipe() {
        return recipe;
    }

    public String getUsername() {
        return username;
    }

    public void setRecipe(Recipe recipe) {
        this.recipe = recipe;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
