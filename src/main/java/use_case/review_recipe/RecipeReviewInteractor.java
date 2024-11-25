package use_case.review_recipe;

import use_case.signup.RecipeReviewOutputData;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * The Recipe Review Interactor.
 */
public class RecipeReviewInteractor {
    private RecipeReviewOutputBoundary reviewPresenter;
    private use_case.review_recipe.RecipeReviewOutputData recipeReviewOutputData;


    public void execute(RecipeReviewInputData recipeReviewInputData) {
        final String recipe = recipeReviewInputData.getName();
        final String comment;
        final int rating;

        comment = "abc";
        rating = 1;

        this.userDataAccessObject = RecipeReviewDataAccessInterface;
        RecipeReviewOutputBoundary recipeReviewOutputBoundary;
        this.reviewPresenter = recipeReviewOutputBoundary;

        reviewPresenter.prepareSuccessView(recipeReviewOutputData);

    }

    public void switchToRecipeHistoryViewView() {
        reviewPresenter.switchToRecipeHistoryView();
    }
}
