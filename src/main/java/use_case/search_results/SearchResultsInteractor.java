package use_case.search_results;

public class SearchResultsInteractor implements SearchResultsInputBoundary{

    private final SearchResultsOutputBoundary searchResultsOutputBoundary;

    public SearchResultsInteractor(SearchResultsOutputBoundary searchResultsOutputBoundary) {
        this.searchResultsOutputBoundary = searchResultsOutputBoundary;
    }

    public void switchToRecipeSearchView() {
        searchResultsOutputBoundary.switchToRecipeSearchView();
    }
}
