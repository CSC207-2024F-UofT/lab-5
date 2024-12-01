package use_case.search_results;

/**
 * The Search Results Interactor.
 */
public class SearchResultsInteractor implements SearchResultsInputBoundary {

    private final SearchResultsOutputBoundary searchResultsOutputBoundary;

    public SearchResultsInteractor(SearchResultsOutputBoundary searchResultsOutputBoundary) {
        this.searchResultsOutputBoundary = searchResultsOutputBoundary;
    }

    @Override
    public void switchToRecipeSearchView() {
        searchResultsOutputBoundary.switchToRecipeSearchView();
    }

    @Override
    public void switchToRecipeDetailsView() {
        searchResultsOutputBoundary.switchToRecipeDetailsView();
    }
}
