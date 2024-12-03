package use_case.saved_recipes;

/**
 * Input Boundary for savedrecipesview.
 */
public interface SavedRecipeInputBoundary {

    /**
     * Executes switch to profile view.
     * @param savedRecipeInputData output data of Saved Recipe View
     */
    void switchToProfileView(SavedRecipeInputData savedRecipeInputData);
}
