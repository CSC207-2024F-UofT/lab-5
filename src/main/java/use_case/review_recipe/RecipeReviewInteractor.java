package use_case.review_recipe;

import use_case.review_recipe.RecipeReviewOutputData;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * The Recipe Review Interactor.
 */
public class RecipeReviewInteractor implements RecipeReviewInputBoundary {
    private RecipeReviewOutputBoundary reviewPresenter;
    private use_case.review_recipe.RecipeReviewOutputData recipeReviewOutputData;

    public RecipeReviewInteractor(RecipeReviewOutputBoundary reviewPresenter) {
        this.reviewPresenter = reviewPresenter;
    }

    public void execute(RecipeReviewInputData recipeReviewInputData) {
        final String recipe = recipeReviewInputData.getName();
        final String comment = recipeReviewInputData.getComment();
        final int rating = recipeReviewInputData.getRating();

        final RecipeReviewOutputBoundary recipeReviewOutputBoundary;
        this.reviewPresenter = recipeReviewOutputBoundary;

        reviewPresenter.prepareSuccessView(recipeReviewOutputData);

    }

    public void switchToRecipeHistoryViewView() {
        reviewPresenter.switchToRecipeHistoryView();
    }
}
