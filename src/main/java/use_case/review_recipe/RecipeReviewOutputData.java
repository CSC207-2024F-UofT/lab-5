package use_case.review_recipe;

public class RecipeReviewOutputData {
    private final String recipeName;
    private final int recipeRating;
    private final String recipeComment;
    private final String message;

    public RecipeReviewOutputData(String recipeName, String recipeComment, int recipeRating, String message) {
        this.recipeName = recipeName;
        this.recipeRating = recipeRating;
        this.recipeComment = recipeComment;
        this.message = message;
    }

    public RecipeReviewOutputData(String name, int rating, String recipeRating) {
        this.recipeName = name;
        this.recipeRating = rating;
        this.recipeComment = recipeRating;
        this.message = "";
    }

    public String getRecipeName() {
        return recipeName;
    }

    public int getRecipeRating() {
        return recipeRating;
    }

    public String getRecipeComment() {
        return recipeComment;
    }

    public String getMessage() {
        return message;
    }
}
