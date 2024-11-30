package interface_adapter.recipe_search;

import interface_adapter.ViewManagerModel;
import interface_adapter.profile.ProfileViewModel;
import interface_adapter.search_results.SearchResultsState;
import interface_adapter.search_results.SearchResultsViewModel;
import use_case.recipe_search.RecipeSearchOutputBoundary;
import use_case.recipe_search.RecipeSearchOutputData;


public class RecipeSearchPresenter implements RecipeSearchOutputBoundary {

    private final RecipeSearchViewModel recipeSearchviewModel;
    private final SearchResultsViewModel searchResultsViewModel;
    private final ProfileViewModel profileViewModel;
    private final ViewManagerModel viewManagerModel;

    public RecipeSearchPresenter(RecipeSearchViewModel recipeSearchviewModel, SearchResultsViewModel searchResultsViewModel, ViewManagerModel viewManagerModel, ProfileViewModel profileViewModel) {
        this.recipeSearchviewModel = recipeSearchviewModel;
        this.searchResultsViewModel = searchResultsViewModel;
        this.viewManagerModel = viewManagerModel;
        this.profileViewModel = profileViewModel;
    }

    @Override
    public void prepareSuccessView(RecipeSearchOutputData outputData) {
        final SearchResultsState searchResultsState = searchResultsViewModel.getState();
        searchResultsState.setSearchResults(outputData.getSearchResults());
        this.searchResultsViewModel.setState(searchResultsState);
        this.searchResultsViewModel.firePropertyChanged();

        this.viewManagerModel.setState(searchResultsViewModel.getViewName());
        this.viewManagerModel.firePropertyChanged();
    }

    @Override
    public void prepareFailView(String error) {
    }

    @Override
    public void switchToResultsView() {
        viewManagerModel.setState(searchResultsViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

    @Override
    public void switchToProfileView(){
        viewManagerModel.setState(profileViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }
}
