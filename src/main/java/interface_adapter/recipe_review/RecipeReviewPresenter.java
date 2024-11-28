package interface_adapter.recipe_review;

import use_case.review_recipe.RecipeReviewOutputBoundary;
import use_case.review_recipe.RecipeReviewOutputData;

public class RecipeReviewPresenter implements RecipeReviewOutputBoundary {

    /**
     * @param outputData the output data
     */
    @Override
    public void prepareSuccessView(RecipeReviewOutputData outputData) {

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

    }
}
