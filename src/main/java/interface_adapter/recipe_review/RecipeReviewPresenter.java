package interface_adapter.recipe_review;

import interface_adapter.ViewManagerModel;
import interface_adapter.profile.ProfileState;
import interface_adapter.profile.ProfileViewModel;
import interface_adapter.saved_recipes.SavedrecipesViewModel;
import use_case.review_recipe.RecipeReviewOutputBoundary;
import use_case.review_recipe.RecipeReviewOutputData;

public class RecipeReviewPresenter implements RecipeReviewOutputBoundary {
    //    private final RecipeReviewViewModel recipeReviewViewModel;
    private final ViewManagerModel viewManagerModel;
    //    private final SavedrecipesViewModel savedRecipesViewModel;
    private final ProfileViewModel profileViewModel;

//    public RecipeReviewPresenter(RecipeReviewViewModel recipeReviewViewModel, ViewManagerModel viewManagerModel) {
//        this.recipeReviewViewModel = recipeReviewViewModel;
//        this.viewManagerModel = viewManagerModel;
//    }

    public RecipeReviewPresenter(ViewManagerModel viewManagerModel, ProfileViewModel profileViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.profileViewModel = profileViewModel;
    }

    /**
     * @param outputData the output data.
     */
//    @Override
//    public void prepareSuccessView(RecipeReviewOutputData outputData) {
//        final RecipeReviewState recipeReviewState = recipeReviewViewModel.getState();
//        recipeReviewViewModel.setState(recipeReviewState);
//    }

    @Override
    public void prepareSuccessView(RecipeReviewOutputData outputData) {
        final ProfileState profileState = profileViewModel.getState();
        profileState.setUsername(outputData.getUsername());
        this.profileViewModel.setState(profileState);
        this.profileViewModel.firePropertyChanged();

        this.viewManagerModel.setState(profileViewModel.getViewName());
        this.viewManagerModel.firePropertyChanged();
    }

    /**
     * @param errorMessage the explanation of the failure
     * */
//    @Override
//    public void prepareFailView(String errorMessage) {
//        final RecipeReviewState recipeReviewState = recipeReviewViewModel.getState();
//        recipeReviewState.setError(errorMessage);
//        recipeReviewViewModel.firePropertyChanged();
//    }

    @Override
    public void prepareFailView(String errorMessage) {

    }

    /**
     *
     */
//    @Override
//    public void switchToSavedrecipesView() {
//        viewManagerModel.setState(savedRecipesViewModel.getViewName());
//        // viewManagerModel.setState(recipeReviewViewModel.getViewName());
//        viewManagerModel.firePropertyChanged();
//    }

    @Override
    public void switchToSavedrecipesView() {

    }
}
