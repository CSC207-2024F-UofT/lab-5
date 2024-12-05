package use_case.nutrition_information;

import entity.Nutrition;

import data_access.NutritionInformationDAO;
import interface_adapter.nutrition_information.NutritionInformationPresenter;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Interactor for handling nutrition information use case.
 * This class acts as the intermediary between the data access layer and the presenter,
 * orchestrating the logic to fetch and process nutrition information for a given recipe.
 */
public class NutritionInformationInteractor implements NutritionInformationInputBoundary {
    // DAO to fetch nutrition data from the data source API
    private final NutritionInformationDAO dataFetcher;

    // Presenter to format and deliver the output data
    private final NutritionInformationPresenter outputPresenter;

    /**
     * Constructor for the NutritionInformationInteractor.
     * @param dataFetcher    DAO responsible for fetching nutrition data.
     * @param outputPresenter Presenter responsible for formatting the output data.
     */
    public NutritionInformationInteractor(NutritionInformationDAO dataFetcher
            ,NutritionInformationPresenter outputPresenter) {
        this.dataFetcher = dataFetcher;
        this.outputPresenter = outputPresenter;
    }

    /**
     * Fetches and processes nutrition information for a given recipe ID.
     * @param inputData Input data containing the recipe ID.
     * @return List of Nutrition objects representing the processed nutrition data.
     */
    @Override
    public List<Nutrition> NutritionInformation(NutritionInformationInputData inputData) {

        // Step 1: Fetch raw JSON nutrition data using the DAO
        String jsonData = dataFetcher.fetchNutritionInformation(inputData.getRecipeId());

        // Step 2: Parse the raw JSON data into a list of Nutrition objects
        List<Nutrition> nutritionList = parseNutritionData(jsonData);

        // Step 3: Wrap the parsed data into the output data object
        NutritionInformationOutputData outputData = new NutritionInformationOutputData(nutritionList);

        // Step 4: Use the presenter to format and deliver the result
        return outputPresenter.presentNutritionInformation(outputData);
    }

    /**
     * Helper Function：Parses raw JSON data into a list of Nutrition objects.
     * @param jsonData Raw JSON string containing nutrition information.
     * @return List of Nutrition objects parsed from the JSON data.
     */
    private List<Nutrition> parseNutritionData(String jsonData) {
        List<Nutrition> nutritionList = new ArrayList<>();

        JSONObject jsonObject = new JSONObject(jsonData);
        if (jsonObject.has("nutrients")) {
            JSONArray nutrients = jsonObject.getJSONArray("nutrients");
            for (int i = 0; i < nutrients.length(); i++) {
                JSONObject nutrientJson = nutrients.getJSONObject(i);

                String name = nutrientJson.getString("name");
                double amount = nutrientJson.getDouble("amount");
                String unit = nutrientJson.getString("unit");
                double percentOfDailyNeeds = nutrientJson.optDouble("percentOfDailyNeeds", 0);

                Nutrition nutrition = new Nutrition(name, amount, unit, percentOfDailyNeeds);
                nutritionList.add(nutrition);
            }
        }
        return nutritionList;
    }
}
