package use_case.profile;

/**
 * Output boundary for the Profile View.
 */
public interface ProfileOutputBoundary {

    /**
     * Executes SavedRecipes switch.
     * @param response Profile Output data
     */
    void switchtoSavedrecipeView(ProfileOutputData response);

    /**
     * Executes Recipe Search switch.
     */
    void switchToRecipeSearchView();
}
