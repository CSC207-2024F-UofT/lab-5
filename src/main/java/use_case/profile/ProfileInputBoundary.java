package use_case.profile;

/**
 * Input boundry of the Profile View.
 */
public interface ProfileInputBoundary {

    /**
     * Executes the switch to savedRecipes use case.
     * @param profileInputData The input data of the profile view
     */
    void switchToSavedRecipesView(ProfileInputData profileInputData);

    /**
     * Executes the switch to Recipesearch use case.
     */
    void switchToRecipeSearchView();
}
