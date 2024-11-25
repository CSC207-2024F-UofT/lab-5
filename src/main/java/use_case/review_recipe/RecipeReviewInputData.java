package use_case.review_recipe;

import entity.Recipe;
import java.util.Map;
import java.util.Set;

/**
 * The Input Data for the Review Recipe Use Case.
 */

public class RecipeReviewInputData {
    private String name;
    private int servings;
    private int calories;
    private Map<String, Integer> nutrients;
    private Set<String> tags;

    private String comment;
    private int rating;

    public RecipeReviewInputData(Recipe recipe, String comment, int rating) {
        this.name = recipe.getName();
        this.servings = recipe.getServings();
        this.calories = recipe.getCalories();
        this.nutrients = recipe.getNutrients();
        this.tags = recipe.getTags();

        this.comment = comment;
        this.rating = rating;
    }

    public String getName() {
        return name;
    }

    public int getServings() {
        return servings;
    }

    public int getCalories() {
        return calories;
    }

    public Map<String, Integer> getNutrients() {
        return nutrients;
    }

    public Set<String> getTags() {
        return tags;
    }

    public String getComment() {
        return comment;
    }

    public int getRating() {
        return rating;
    }
}
