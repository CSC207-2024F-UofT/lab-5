package interface_adapter.nutrition_information;

import java.util.List;

import entity.Nutrition;
import use_case.nutrition_information.NutritionInformationInputBoundary;
import use_case.nutrition_information.NutritionInformationInputData;

/**
 * Controller for the Nutrition Information Use Case.
 */
public class NutritionInformationController {
    private final NutritionInformationInputBoundary nutritionInformationInteractor;

    public NutritionInformationController(NutritionInformationInputBoundary nutritionInformationInteractor) {
        this.nutritionInformationInteractor = nutritionInformationInteractor;
    }

    /**
     * Executes the Nutrition Information Use Case.
     * @param recipeId The ID of the recipe for which nutrition information is to be fetched.
     * @return List of Nutrition objects representing the processed nutrition data.
     */
    public List<Nutrition> execute(int recipeId) {
        // Create the input data object
        final NutritionInformationInputData inputData = new NutritionInformationInputData(recipeId);

        // Call the interactor to fetch and process nutrition information
        return nutritionInformationInteractor.NutritionInformation(inputData);
    }
}

