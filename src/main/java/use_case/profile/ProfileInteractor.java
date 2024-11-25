package use_case.profile;

import entity.Recipe;

import java.util.HashMap;
import java.util.Map;

public class ProfileInteractor implements ProfileInputBoundary {
    private final ProfileOutputBoundary userPresenter;

    public ProfileInteractor(ProfileOutputBoundary profileOutputBoundary) {
        this.userPresenter = profileOutputBoundary;
    }

    public void execute(ProfileInputData profileInputData) {
        final Map<Recipe, Integer> map = new HashMap<>();
        final ProfileOutputData profileOutputData = new ProfileOutputData(map, false);
        userPresenter.prepareSuccessView(profileOutputData);
    }

    public void switchToSavedRecipesView() {
        userPresenter.switchtoSavedrecipeView();
    }

    public void switchToRecipeSearchView() {
        userPresenter.switchToRecipeSearchView();
    }
}
