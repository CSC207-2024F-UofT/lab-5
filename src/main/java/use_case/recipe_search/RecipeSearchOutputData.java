package use_case.recipe_search;

import java.util.List;

import entity.Recipe;

/**
 * Output Data for the Search Use Case.
 */
public class RecipeSearchOutputData {

    private final List<Recipe> searchResults;

    private final boolean useCaseFailed;

    public RecipeSearchOutputData(List<Recipe> searchResults, boolean useCaseFailed) {
        this.searchResults = searchResults;
        this.useCaseFailed = useCaseFailed;
    }

    public List<Recipe> getSearchResults() {
        return searchResults;
    }

    public boolean isUseCaseFailed() {
        return useCaseFailed;
    }
}
