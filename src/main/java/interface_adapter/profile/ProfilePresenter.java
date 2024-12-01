package interface_adapter.profile;

import interface_adapter.ViewManagerModel;
import interface_adapter.recipe_search.RecipeSearchViewModel;
import interface_adapter.saved_recipes.SavedrecipesState;
import interface_adapter.saved_recipes.SavedrecipesViewModel;
import use_case.profile.ProfileOutputBoundary;
import use_case.profile.ProfileOutputData;

/**
 * The Presenter for the Profile View.
 */
public class ProfilePresenter implements ProfileOutputBoundary {

    private final ProfileViewModel profileViewModel;
    private final SavedrecipesViewModel savedrecipesViewModel;
    private final ViewManagerModel viewManagerModel;
    private final RecipeSearchViewModel recipeSearchViewModel;

    public ProfilePresenter(SavedrecipesViewModel savedrecipesViewModel, ViewManagerModel viewManagerModel, RecipeSearchViewModel recipeSearchViewModel) {
        this.savedrecipesViewModel = savedrecipesViewModel;
        this.viewManagerModel = viewManagerModel;
        this.profileViewModel = new ProfileViewModel();
        this.recipeSearchViewModel = recipeSearchViewModel;
    }

    /**
     * Switch to SavedRecipes View.
     * @param response Output data of profile view
     */
    public void switchtoSavedrecipeView(ProfileOutputData response) {
        final SavedrecipesState savedrecipesState = savedrecipesViewModel.getState();
        savedrecipesState.setUsername(response.getUsername());
        savedrecipesState.setUsername(response.getUsername());
        this.savedrecipesViewModel.setState(savedrecipesState);
        this.savedrecipesViewModel.firePropertyChanged();
        viewManagerModel.setState(savedrecipesViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

    /**
     * Switch to RecipeSearch View.
     */
    public void switchToRecipeSearchView() {
        viewManagerModel.setState(recipeSearchViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }
}
