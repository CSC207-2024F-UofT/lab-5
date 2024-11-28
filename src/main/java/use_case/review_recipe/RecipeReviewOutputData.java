package use_case.review_recipe;

public class RecipeReviewOutputData {
    private final String recipeName;
    private final String recipeComment;
    private final String recipeRating;
    private final String message;

    public RecipeReviewOutputData(String recipeName, String recipeComment, String recipeRating, String message) {
        this.recipeName = recipeName;
        this.recipeComment = recipeComment;
        this.recipeRating = recipeRating;
        this.message = message;
    }

    public String getRecipeName() {
        return recipeName;
    }

    public String getRecipeComment() {
        return recipeComment;
    }

    public String getRecipeRating() {
        return recipeRating;
    }

    public String getMessage() {
        return message;
    }
}
