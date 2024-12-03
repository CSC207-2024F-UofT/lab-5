package use_case.search_results;

/**
 * The output boundary for the Search Results Use Case.
 */
public interface SearchResultsOutputBoundary {

    /**
     * Switches to the Recipe Search View.
     */
    void switchToRecipeSearchView();

    /**
     * Switches to the Recipe Details View.
     */
    void switchToRecipeDetailsView();
}
