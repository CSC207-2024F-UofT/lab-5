package interface_adapter.saved_recipes;

import entity.Recipe;
import use_case.saved_recipes.SavedRecipeInputBoundary;
import use_case.saved_recipes.SavedRecipeInputData;

import java.util.Map;

public class SavedrecipesController {

    private final SavedRecipeInputBoundary savedRecipeInputBoundary;

    public SavedrecipesController(SavedRecipeInputBoundary savedRecipeInputBoundary) {
        this.savedRecipeInputBoundary = savedRecipeInputBoundary;
    }

    public void switchToProfileView(String username) {
        final SavedRecipeInputData savedRecipeInputData = new SavedRecipeInputData(username);
        savedRecipeInputBoundary.switchToProfileView(savedRecipeInputData);
    }
}
