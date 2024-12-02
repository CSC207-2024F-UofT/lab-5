package use_case.review_recipe;

import entity.User;

public interface RecipeReviewDataAccessInterface {

    String getCurrentUsername();

    User get(String username);
}
