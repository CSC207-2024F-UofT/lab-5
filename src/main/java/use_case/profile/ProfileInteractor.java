package use_case.profile;

import entity.Recipe;
import entity.User;

import java.util.HashMap;
import java.util.Map;

public class ProfileInteractor implements ProfileInputBoundary {
    private final ProfileOutputBoundary userPresenter;

    public ProfileInteractor(ProfileOutputBoundary profileOutputBoundary) {
        this.userPresenter = profileOutputBoundary;
    }

    public void switchToSavedRecipesView(ProfileInputData profileInputData) {
        final String username = profileInputData.getUsername();
        final Map<Recipe, Integer> savedRecipes = new HashMap<>();
        final ProfileOutputData profileOutputData = new ProfileOutputData(username, savedRecipes, false);
        userPresenter.switchtoSavedrecipeView(profileOutputData);
    }

    public void switchToRecipeSearchView() {
        userPresenter.switchToRecipeSearchView();
    }
}
