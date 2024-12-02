package use_case.review_recipe;

import entity.CommonUser;
import entity.Recipe;
import entity.User;
import use_case.profile.ProfileDataAccessInterface;

/**
 * The Recipe Review Interactor.
 */
public class RecipeReviewInteractor implements ReviewRecipeInputBoundary {
    private final RecipeReviewOutputBoundary reviewPresenter;
    private final RecipeReviewDataAccessInterface userDataAccess;

    public RecipeReviewInteractor(RecipeReviewOutputBoundary reviewPresenter, RecipeReviewDataAccessInterface recipeReviewDataAccessInterface) {
        this.reviewPresenter = reviewPresenter;
        this.userDataAccess = recipeReviewDataAccessInterface;
    }

    /**
     * Executes the recipe review use case.
     * @param recipeReviewInputData the input data
     */
//    public void execute(RecipeReviewInputData recipeReviewInputData) {
//        final Recipe recipe = recipeReviewInputData.getRecipe();
//        final int rating = recipeReviewInputData.getRating();
//        final RecipeReviewOutputData recipeReviewOutputData;
//
//        try {
//            user.reviewRecipe(recipe, rating);
//            recipeReviewOutputData = new RecipeReviewOutputData(
//                    recipe.getName(),
//                    rating,
//                    "Review added."
//            );
//            reviewPresenter.prepareSuccessView(recipeReviewOutputData);
//        }
//        catch (IllegalArgumentException ex) {
//            reviewPresenter.prepareFailView("Review could not be added.");
//        }
//    }


    public void execute(RecipeReviewInputData recipeReviewInputData) {
        final String username = userDataAccess.getCurrentUsername();
        final User user = userDataAccess.get(username);
        final Recipe recipe = recipeReviewInputData.getRecipe();
        final int rating = recipeReviewInputData.getRating();
        final Integer ratingInteger = Integer.valueOf(rating);
        user.addRecipe(recipe, ratingInteger);
        final RecipeReviewOutputData recipeReviewOutputData = new RecipeReviewOutputData(username);
        reviewPresenter.prepareSuccessView(recipeReviewOutputData);

    }

    /**
     * Switches to the SavedRecipes View.
     *
     */
    public void switchToSavedrecipesView() {
        reviewPresenter.switchToSavedrecipesView();
    }
}
