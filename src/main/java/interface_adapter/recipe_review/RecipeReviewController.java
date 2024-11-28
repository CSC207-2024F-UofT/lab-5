package interface_adapter.recipe_review;

import entity.Recipe;
import use_case.review_recipe.RecipeReviewInputData;
import use_case.review_recipe.ReviewRecipeInputBoundary;

/**
 * The controller for the Review Recipe Use Case.
 */
public class RecipeReviewController {
    private final ReviewRecipeInputBoundary reviewRecipeUseCaseInteractor;

    public RecipeReviewController(ReviewRecipeInputBoundary reviewRecipeUseCaseInteractor) {
        this.reviewRecipeUseCaseInteractor = reviewRecipeUseCaseInteractor;
    }

    /**
     * Executes the Review Recipe Use Case.
     * @param recipe the recipe that the user is reviewing
     * @param rating the rating that the user assigns to the recipe
     */
    public void execute(Recipe recipe, int rating) {
        final RecipeReviewInputData recipeReviewInputData = new RecipeReviewInputData(recipe, rating);

        reviewRecipeUseCaseInteractor.execute(recipeReviewInputData);
    }

    public void switchToRecipeHistoryView() {
        reviewRecipeUseCaseInteractor.switchToRecipeHistoryView();
    }

}
