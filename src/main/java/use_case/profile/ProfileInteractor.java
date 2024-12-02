package use_case.profile;

import java.util.Map;

import entity.Recipe;

/**
 * Interactor for the Profile View.
 */
public class ProfileInteractor implements ProfileInputBoundary {
    private final ProfileOutputBoundary userPresenter;
    private final ProfileDataAccessInterface userDataAccess;

    public ProfileInteractor(ProfileOutputBoundary profileOutputBoundary, ProfileDataAccessInterface userDataAccess) {
        this.userPresenter = profileOutputBoundary;
        this.userDataAccess = userDataAccess;
    }

    /**
     * Executes switch to SavedRecipes.
     * @param profileInputData Input data of Profile view.
     */
    public void switchToSavedRecipesView(ProfileInputData profileInputData) {
        final String username = profileInputData.getUsername();
        final Map<Recipe, Integer> savedRecipes = userDataAccess.get(username).getRecipes();
        final ProfileOutputData profileOutputData = new ProfileOutputData(username, savedRecipes, false);
        userPresenter.switchtoSavedrecipeView(profileOutputData);
    }

    /**
     * Execute the switch to RecipeSearchView.
     */
    public void switchToRecipeSearchView() {
        userPresenter.switchToRecipeSearchView();
    }
}
