package interface_adapter.search_results;

import use_case.search_results.SearchResultsInputBoundary;

/**
 * The controller for the Search Results Use Case.
 */
public class SearchResultsController {

    private final SearchResultsInputBoundary searchResultsInputBoundary;

    public SearchResultsController(SearchResultsInputBoundary searchResultsInputBoundary) {
        this.searchResultsInputBoundary = searchResultsInputBoundary;
    }

    /**
     * Executes the "switch to RecipeSearchView" Use Case.
     */
    public void switchToRecipeSearchView() {
        this.searchResultsInputBoundary.switchToRecipeSearchView();
    }

    /**
     * Executes the "switch to RecipeDetailsView" Use Case.
     */
    public void switchToRecipeDetailsView() {
        this.searchResultsInputBoundary.switchToRecipeDetailsView();
    }
}
