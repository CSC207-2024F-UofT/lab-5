package interface_adapter.recipe_review;

import entity.Recipe;

/**
 * The State for the Recipe Review View Model.
 */
public class RecipeReviewState {
    private Recipe recipe;
    private String name;
    private int rating;

    public Recipe getRecipe() {
        return recipe;
    }

    public void setRecipe(Recipe recipe) {
        this.recipe = recipe;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

}
