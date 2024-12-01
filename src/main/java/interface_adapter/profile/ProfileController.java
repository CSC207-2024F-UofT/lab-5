package interface_adapter.profile;

import use_case.profile.ProfileInputBoundary;
import use_case.profile.ProfileInputData;

/**
 * The Controller for the Profile View.
 */
public class ProfileController {

    private final ProfileInputBoundary profileInputBoundary;

    public ProfileController(ProfileInputBoundary profileInputBoundry) {
        this.profileInputBoundary = profileInputBoundry;
    }

    /**
     * Switch to SavedRecipes View.
     * @param username the username of currently logged-in user.
     */
    public void switchToSavedRecipesView(String username) {
        final ProfileInputData profileInputData = new ProfileInputData(username);
        this.profileInputBoundary.switchToSavedRecipesView(profileInputData);
    }

    /**
     * Switch to RecipeSearch View.
     */
    public void switchToRecipeSearchView() {
        this.profileInputBoundary.switchToRecipeSearchView();
    }
}
