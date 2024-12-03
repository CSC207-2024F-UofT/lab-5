package interface_adapter.search_recipe_list_by_name;

import interface_adapter.RecipeListState;
import interface_adapter.RecipeListViewModel;
import use_case.search_recipe_list_by_name.SearchRecipeListByNameOutputBoundary;
import use_case.search_recipe_list_by_name.SearchRecipeListByNameOutputData;

/**
 * The Presenter for the Search Recipe List By Name Use Case.
 */
public class SearchRecipeListByNamePresenter implements SearchRecipeListByNameOutputBoundary {

    private final RecipeListViewModel recipeListViewModel;

    public SearchRecipeListByNamePresenter(RecipeListViewModel recipeListViewModel) {
        this.recipeListViewModel = recipeListViewModel;
    }

    @Override
    public void prepareSuccessView(SearchRecipeListByNameOutputData outputData) {
        // TODO
        final RecipeListState recipeListState = recipeListViewModel.getState();
        recipeListState.setRecipeList(outputData.getRecipes());
        recipeListViewModel.firePropertyChanged("recipes");
    }

    @Override
    public void prepareFailView(String error) {
        // note: this use case currently can't fail
    }
}
