package use_case.review_recipe;

import entity.CommonUser;
import entity.Recipe;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * The Recipe Review Interactor.
 */
public class RecipeReviewInteractor implements RecipeReviewInputBoundary {
    private final RecipeReviewOutputBoundary reviewPresenter;
    private CommonUser user;

    public RecipeReviewInteractor(RecipeReviewOutputBoundary reviewPresenter, CommonUser user) {
        this.reviewPresenter = reviewPresenter;
        this.user = user;
    }

    /**
     * Executes the recipe review use case.
     * @param recipeReviewInputData the input data
     */
    public void execute(RecipeReviewInputData recipeReviewInputData) {
        final Recipe recipe = recipeReviewInputData.getRecipe();
        final int rating = recipeReviewInputData.getRating();
        // final String comment = recipeReviewInputData.getComment();

        RecipeReviewOutputData recipeReviewOutputData;

        try {
            user.reviewRecipe(recipe, rating, //comment);
            recipeReviewOutputData = new RecipeReviewOutputData(
                    recipe.getName(),
                    rating,
                    // comment,
                    "Review added."
            ),
            reviewPresenter.prepareSuccessView(recipeReviewOutputData);
        } catch (IllegalArgumentException e) {
            recipeReviewOutputData = new RecipeReviewOutputData(
                    recipe.getName(),
                    rating,
                    // comment,
                    "Failure in adding review." + e.getMessage()
            );
            reviewPresenter.prepareFailView("Review could not be added.");
        }
    }

    public void switchToRecipeHistoryViewView() {
        reviewPresenter.switchToRecipeHistoryView();
    }
}
