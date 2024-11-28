package use_case.review_recipe;

import entity.Recipe;
import java.util.Map;
import java.util.Set;

/**
 * The Input Data for the Review Recipe Use Case.
 */

public class RecipeReviewInputData {
    private final Recipe recipe;
    private final String name;
    private final int rating;
    private final String comment;

    public RecipeReviewInputData(Recipe recipe, String comment, int rating) {
        this.recipe = recipe;
        this.name = recipe.getName();
        this.rating = rating;
        this.comment = comment;
    }

    public Recipe getRecipe() {
        return recipe;
    }

    public String getName() {
        return name;
    }

    public int getRating() {
        return rating;
    }

    public String getComment() {
        return comment;
    }
}
