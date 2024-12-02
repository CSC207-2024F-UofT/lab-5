package use_case.saved_recipes;

/**
 * Interactor for SavedRecipe View.
 */
public class SavedRecipeInteractor implements SavedRecipeInputBoundary {

    private final SavedRecipeOutputBoundry savedRecipeOutputBoundry;

    public SavedRecipeInteractor(SavedRecipeOutputBoundry savedRecipeOutputBoundry) {
        this.savedRecipeOutputBoundry = savedRecipeOutputBoundry;
    }

    /**
     * Switches to Profile View.
     * @param savedRecipeInputData Input data associated with SavedRecipe View.
     */
    public void switchToProfileView(SavedRecipeInputData savedRecipeInputData) {
        final SavedRecipeOutputData savedRecipeOutputData = new SavedRecipeOutputData(savedRecipeInputData
                .getUsername(), false);
        savedRecipeOutputBoundry.switchToProfileView(savedRecipeOutputData);
    }
}
