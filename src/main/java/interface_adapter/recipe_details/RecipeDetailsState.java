package interface_adapter.recipe_details;

import entity.Recipe;

/**
 * The state for the Recipe Details View Model.
 */
public class RecipeDetailsState {
    // private int rating;
    private Recipe recipe;

    public RecipeDetailsState(Recipe recipe) {
        this.recipe = recipe;
    }

    public Recipe getRecipe() {
        return recipe;
    }

    public void setRecipe(Recipe recipe) {
        this.recipe = recipe;
    }
}
