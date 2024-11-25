package use_case.review_recipe;

public class RecipeReviewOutputData {
    private final String recipeName;
    private final String recipeComment;
    private final String recipeRating;

    public RecipeReviewOutputData(String recipeName, String recipeComment, String recipeRating) {
        this.recipeName = recipeName;
        this.recipeComment = recipeComment;
        this.recipeRating = recipeRating;
    }
}
