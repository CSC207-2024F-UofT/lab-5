package interface_adapter.recipe_review;

import entity.Recipe;
import use_case.review_recipe.RecipeReviewInputData;
import use_case.review_recipe.ReviewRecipeInputBoundary;

public class RecipeReviewController {
    private final ReviewRecipeInputBoundary ReviewRecipeUseCaseInteractor;

    public RecipeReviewController(ReviewRecipeInputBoundary ReviewRecipeUseCaseInteractor) {
        this.ReviewRecipeUseCaseInteractor = ReviewRecipeUseCaseInteractor;
    }

    public void execute(Recipe recipe, String comment, int rating) {
        final RecipeReviewInputData recipeReviewInputData = new RecipeReviewInputData(recipe, comment, rating);

        ReviewRecipeUseCaseInteractor.execute(recipeReviewInputData);
    }

    public void switchToRecipeHistoryView() {
        ReviewRecipeUseCaseInteractor.switchToRecipeHistoryView();
    }

}
