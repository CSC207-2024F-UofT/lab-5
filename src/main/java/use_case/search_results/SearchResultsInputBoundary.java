package use_case.search_results;

/**
 * Input Boundary for actions which are related to Search Results.
 */
public interface SearchResultsInputBoundary {

    /**
     * Executes the switch to Recipe Search view use case.
     */
    void switchToRecipeSearchView();

    /**
     * Executes the switch to Recipe Details use case.
     */
    void switchToRecipeDetailsView();
}
