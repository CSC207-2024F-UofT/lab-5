package use_case.review_recipe;

public interface ReviewRecipeInputBoundary {
    void execute(RecipeReviewInputData recipeReviewInputData);

    void switchToRecipeHistoryView();
}
