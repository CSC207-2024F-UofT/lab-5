package interface_adapter.saved_recipes;

import interface_adapter.ViewManagerModel;
import interface_adapter.profile.ProfileState;
import interface_adapter.profile.ProfileViewModel;
import use_case.saved_recipes.SavedRecipeOutputBoundry;
import use_case.saved_recipes.SavedRecipeOutputData;
import view.SavedrecipesView;
import view.ViewManager;

public class SavedrecipesPresenter implements SavedRecipeOutputBoundry {

    private final SavedrecipesViewModel savedrecipesViewModel;
    private final ProfileViewModel profileViewModel;
    private final ViewManagerModel viewManagerModel;

    public SavedrecipesPresenter(SavedrecipesViewModel savedrecipesViewModel, ProfileViewModel profileViewModel, ViewManagerModel viewManagerModel) {
        this.savedrecipesViewModel = savedrecipesViewModel;
        this.profileViewModel = profileViewModel;
        this.viewManagerModel = viewManagerModel;
    }

    @Override
    public void switchToProfileView(SavedRecipeOutputData response) {
        final ProfileState profileState = profileViewModel.getState();
        profileState.setUsername(response.getUsername());

        this.profileViewModel.setState(profileState);
        this.profileViewModel.firePropertyChanged();

        this.viewManagerModel.setState(this.profileViewModel.getViewName());
        this.viewManagerModel.firePropertyChanged();
    }
}
