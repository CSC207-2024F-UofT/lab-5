package use_case.profile;

import entity.Recipe;
import entity.User;

import java.util.HashMap;
import java.util.Map;

public class ProfileInteractor implements ProfileInputBoundary {
    private final ProfileOutputBoundary userPresenter;
    private final ProfileDataAccessInterface userDataAccess;

    public ProfileInteractor(ProfileOutputBoundary profileOutputBoundary, ProfileDataAccessInterface userDataAccess) {
        this.userPresenter = profileOutputBoundary;
        this.userDataAccess = userDataAccess;
    }

    public void switchToSavedRecipesView(ProfileInputData profileInputData) {
        final String username = profileInputData.getUsername();
        final Map<Recipe, Integer> savedRecipes = userDataAccess.get(username).getRecipes();
        final ProfileOutputData profileOutputData = new ProfileOutputData(username, savedRecipes, false);
        userPresenter.switchtoSavedrecipeView(profileOutputData);
    }

    public void switchToRecipeSearchView() {
        userPresenter.switchToRecipeSearchView();
    }
}
