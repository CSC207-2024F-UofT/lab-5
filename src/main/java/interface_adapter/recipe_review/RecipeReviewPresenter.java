package interface_adapter.recipe_review;

import interface_adapter.ViewManagerModel;
import use_case.review_recipe.RecipeReviewOutputBoundary;
import use_case.review_recipe.RecipeReviewOutputData;

public class RecipeReviewPresenter implements RecipeReviewOutputBoundary {
    private RecipeReviewViewModel recipeReviewViewModel;
    private ViewManagerModel viewManagerModel;

    public RecipeReviewPresenter(RecipeReviewViewModel recipeReviewViewModel, ViewManagerModel viewManagerModel) {
        this.recipeReviewViewModel = recipeReviewViewModel;
        this.viewManagerModel = viewManagerModel;
    }

    /**
     * @param outputData the output data
     */
    @Override
    public void prepareSuccessView(RecipeReviewOutputData outputData) {
        final RecipeReviewState recipeReviewState = recipeReviewViewModel.getState();
        recipeReviewState.setRecipeReview(recipeReviewViewModel.setState(recipeReviewState));
    }

    /**
     * @param errorMessage the explanation of the failure
     */
    @Override
    public void prepareFailView(String errorMessage) {

    }

    /**
     *
     */
    @Override
    public void switchToRecipeHistoryView() {
        viewManagerModel.setState(recipeReviewViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }
}
