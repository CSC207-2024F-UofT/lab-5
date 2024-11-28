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

    private final String comment;
    private final int rating;

    public RecipeReviewInputData(Recipe recipe, String comment, int rating) {
        this.recipe = recipe;
        this.name = recipe.getName();

        this.comment = comment;
        this.rating = rating;
    }

    public Recipe getRecipe() {
        return recipe;
    }

    public String getName() {
        return name;
    }

    public String getComment() {
        return comment;
    }

    public int getRating() {
        return rating;
    }
}
