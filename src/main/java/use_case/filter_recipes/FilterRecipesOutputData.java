package use_case.filter_recipes;

import java.util.List;

import entity.Recipe;

/**
 * Output data for the filter recipes use case.
 */
public class FilterRecipesOutputData {

    private final List<Recipe> recipes;

    public FilterRecipesOutputData(List<Recipe> recipes) {
        this.recipes = recipes;
    }

    public List<Recipe> getRecipes() {
        return recipes;
    }
}
