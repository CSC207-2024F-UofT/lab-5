package use_case.search_recipe_list_by_name;

import entity.Recipe;

import java.util.List;

/**
 * Output Data for the Search Recipe List By Name Use Case.
 */
public class SearchRecipeListByNameOutputData {

    private final List<Recipe> recipes;

    private final boolean useCaseFailed;

    public SearchRecipeListByNameOutputData(List<Recipe> recipes, boolean useCaseFailed) {
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
