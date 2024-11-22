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

    public RecipeReviewInputData(Recipe recipe) {
        this.name = recipe.getName();
        this.servings = recipe.getServings();
        this.calories = recipe.getCalories();
        this.nutrients = recipe.getNutrients();
        this.tags = recipe.getTags();
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
}
