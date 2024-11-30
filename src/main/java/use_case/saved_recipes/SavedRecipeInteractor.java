package use_case.saved_recipes;

import entity.Recipe;

import java.util.Map;

public class SavedRecipeInteractor implements SavedRecipeInputBoundary {

    private final SavedRecipeOutputBoundry savedRecipeOutputBoundry;

    public SavedRecipeInteractor(SavedRecipeOutputBoundry savedRecipeOutputBoundry) {
        this.savedRecipeOutputBoundry = savedRecipeOutputBoundry;
    }

    public void switchToProfileView(SavedRecipeInputData savedRecipeInputData) {
        final SavedRecipeOutputData savedRecipeOutputData = new SavedRecipeOutputData(savedRecipeInputData.getUsername(), false);
        savedRecipeOutputBoundry.switchToProfileView(savedRecipeOutputData);
    }
}
