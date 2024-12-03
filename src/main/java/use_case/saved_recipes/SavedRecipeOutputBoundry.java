package use_case.saved_recipes;

/**
 * Output boundry for SavedRecipe View.
 */
public interface SavedRecipeOutputBoundry {

    /**
     * Execute switch to Profile View.
     * @param outputData Output data associated with saved recipe view.
     */
    void switchToProfileView(SavedRecipeOutputData outputData);
}
