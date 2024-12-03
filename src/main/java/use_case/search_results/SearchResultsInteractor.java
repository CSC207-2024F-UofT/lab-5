package use_case.search_results;

import entity.Recipe;
import entity.User;

/**
 * The Search Results Interactor.
 */
public class SearchResultsInteractor implements SearchResultsInputBoundary {
    private final SearchResultsDataAccessInterface userDataAccessInterface;

    private final SearchResultsOutputBoundary searchResultsOutputBoundary;

    public SearchResultsInteractor(SearchResultsOutputBoundary searchResultsOutputBoundary, SearchResultsDataAccessInterface userDataAccessInterface) {
        this.searchResultsOutputBoundary = searchResultsOutputBoundary;
        this.userDataAccessInterface = userDataAccessInterface;
    }

    @Override
    public void switchToRecipeSearchView() {
        searchResultsOutputBoundary.switchToRecipeSearchView();
    }

    @Override
    public void switchToRecipeDetailsView(SearchResultsInputData searchResultsInputData) {
        final String username = userDataAccessInterface.getCurrentUsername();
        final Recipe recipe = searchResultsInputData.getRecipe();
        final SearchResultsOutputData searchResultsOutputData = new SearchResultsOutputData(username, recipe);
        searchResultsOutputBoundary.switchToRecipeDetailsView(searchResultsOutputData);
    }
}
