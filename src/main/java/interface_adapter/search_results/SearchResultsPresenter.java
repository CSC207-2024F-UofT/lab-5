package interface_adapter.search_results;

import interface_adapter.ViewManagerModel;
import interface_adapter.recipe_details.RecipeDetailsState;
import interface_adapter.recipe_details.RecipeDetailsViewModel;
import interface_adapter.recipe_search.RecipeSearchViewModel;
import use_case.search_results.SearchResultsOutputBoundary;
import use_case.search_results.SearchResultsOutputData;

/**
 * The Presenter for the Search Results Use Case.
 */
public class SearchResultsPresenter implements SearchResultsOutputBoundary {

    private final SearchResultsViewModel searchResultsViewModel;
    private final RecipeDetailsViewModel recipeDetailsViewModel;
    private final ViewManagerModel viewManagerModel;
    private final RecipeSearchViewModel recipeSearchViewModel;

    public SearchResultsPresenter(SearchResultsViewModel searchResultsViewModel, RecipeDetailsViewModel recipeDetailsViewModel, ViewManagerModel viewManagerModel , RecipeSearchViewModel recipeSearchViewModel) {
        this.searchResultsViewModel = searchResultsViewModel;
        this.recipeDetailsViewModel = recipeDetailsViewModel;
        this.viewManagerModel = viewManagerModel;
        this.recipeSearchViewModel = recipeSearchViewModel;
    }

    @Override
    public void switchToRecipeSearchView() {
        viewManagerModel.setState(recipeSearchViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

    @Override
    public void switchToRecipeDetailsView(SearchResultsOutputData response) {
        final RecipeDetailsState recipeDetailsState = recipeDetailsViewModel.getState();
        recipeDetailsState.setRecipe(response.getRecipe());
        recipeDetailsState.setUsername(response.getUsername());
        this.recipeDetailsViewModel.setState(recipeDetailsState);
        this.recipeDetailsViewModel.firePropertyChanged();
        viewManagerModel.setState(recipeDetailsViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

}
