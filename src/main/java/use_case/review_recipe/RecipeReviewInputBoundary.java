package use_case.review_recipe;

public interface RecipeReviewInputBoundary {

    /**
     * Executes the RecipeReview use case.
     * @param recipeReviewInputData the input data
     */
    void execute(RecipeReviewInputData recipeReviewInputData);
}
