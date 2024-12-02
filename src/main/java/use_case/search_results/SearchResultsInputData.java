package use_case.search_results;

import entity.Recipe;

/**
 * The Input Data for the Search Results use Case.
 */
public class SearchResultsInputData {
    private Recipe recipe;

    public SearchResultsInputData(Recipe recipe) {
        this.recipe = recipe;
    }

    public Recipe getRecipe() {
        return recipe;
    }
}
