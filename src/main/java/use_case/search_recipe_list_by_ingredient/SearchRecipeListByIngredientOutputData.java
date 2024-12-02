package use_case.search_recipe_list_by_ingredient;

import entity.Recipe;

import java.util.List;

/**
 * Output Data for the Search Recipe List By Ingredient Use Case.
 */
public class SearchRecipeListByIngredientOutputData {

    private final List<Recipe> recipes;

    private final boolean useCaseFailed;

    public SearchRecipeListByIngredientOutputData(List<Recipe> recipes, boolean useCaseFailed) {
        this.recipes = recipes;
        this.useCaseFailed = useCaseFailed;
    }

    public List<Recipe> getRecipes() {
        return recipes;
    }

    public boolean isUseCaseFailed() {
        return useCaseFailed;
    }
}
