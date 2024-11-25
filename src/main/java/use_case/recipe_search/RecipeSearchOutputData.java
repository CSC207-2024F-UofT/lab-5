package use_case.recipe_search;

import entity.Recipe;

import java.util.List;

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
