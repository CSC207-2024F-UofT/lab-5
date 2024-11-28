package use_case.review_recipe;

import entity.CommonUser;
import entity.Recipe;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * The Recipe Review Interactor.
 */
public class RecipeReviewInteractor implements RecipeReviewInputBoundary {
    private RecipeReviewOutputBoundary reviewPresenter;
    private RecipeReviewOutputData recipeReviewOutputData;
    private CommonUser user;

    public RecipeReviewInteractor(RecipeReviewOutputBoundary reviewPresenter) {
        this.reviewPresenter = reviewPresenter;
    }

    /**
     * Executes the recipe review use case
     * @param recipeReviewInputData the input data
     */
    public void execute(RecipeReviewInputData recipeReviewInputData) {

        final Recipe recipe = recipeReviewInputData.getRecipe();
        // final String comment = recipeReviewInputData.getComment();
        final int rating = recipeReviewInputData.getRating();

        try {
            user.reviewRecipe(recipe, rating);
            recipeReviewOutputData = new RecipeReviewOutputData(
                    recipe.getName(),
                    rating,
                    "Review added."
            );
            reviewPresenter.prepareSuccessView(recipeReviewOutputData);
        } catch (IllegalArgumentException e) {
            recipeReviewOutputData = new RecipeReviewOutputData(
                    recipe.getName(),
                    rating,
                    "Failure in adding review."
            );
            reviewPresenter.prepareFailView("Failure in adding review.");
        }
    }

    public void switchToRecipeHistoryViewView() {
        reviewPresenter.switchToRecipeHistoryView();
    }
}
