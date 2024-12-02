package interface_adapter.saved_recipes;

import use_case.saved_recipes.SavedRecipeInputBoundary;
import use_case.saved_recipes.SavedRecipeInputData;

/**
 * The controller for the Saved Recipes view.
 */
public class SavedrecipesController {

    private final SavedRecipeInputBoundary savedRecipeInputBoundary;

    public SavedrecipesController(SavedRecipeInputBoundary savedRecipeInputBoundary) {
        this.savedRecipeInputBoundary = savedRecipeInputBoundary;
    }

    /**
     * Switch to the Profile view.
     * @param username username of currently logged-in User.
     */
    public void switchToProfileView(String username) {
        final SavedRecipeInputData savedRecipeInputData = new SavedRecipeInputData(username);
        savedRecipeInputBoundary.switchToProfileView(savedRecipeInputData);
    }
}
