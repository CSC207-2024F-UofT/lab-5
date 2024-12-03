package data_access;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import use_case.nutrition_information.NutritionInformationAccessInterface;

import java.io.IOException;

/**
 * Data Access Object (DAO) implementation for fetching nutrition information.
 * This class communicates with the Spoonacular API to retrieve nutrition data
 * for a specific recipe based on its ID.
 */
public class NutritionInformationDAO implements NutritionInformationAccessInterface {

    // Base URL for the Spoonacular API
    private static final String BASE_URL = "https://api.spoonacular.com";

    // API key for authenticating requests to the Spoonacular API
    private static final String API_KEY = AppConstants.API_KEY;

    /**
     * Fetches nutrition information for a specific recipe from the API.
     * @param recipeId The ID of the recipe for which nutrition information is required.
     * @return A JSON string containing the nutrition information.
     * @throws RuntimeException If there is an error during the API request.
     */
    @Override
    public String fetchNutritionInformation(int recipeId) {
        // Step 1: Initialize OkHttpClient for making HTTP requests
        OkHttpClient client = new OkHttpClient();

        // Step 2: Build the URL for the API request
        String url = buildUrl(recipeId);

        // Step 3: Create a new HTTP request object
        Request request = new Request.Builder().url(url).build();

        // Step 4: Execute the HTTP request and handle the response
        try (Response response = client.newCall(request).execute()) {
            if (response.isSuccessful() && response.body() != null) {
                return response.body().string();
            } else {
                throw new IOException("Failed to fetch data: HTTP code " + response.code());
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Builds the complete URL for the Spoonacular API request.
     * @param recipeId The ID of the recipe for which the URL is generated.
     * @return A fully formed URL string for the API request.
     */
    private String buildUrl(int recipeId) {

        // Construct the API endpoint specific to the recipe
        String endpoint = "/recipes/" + recipeId + "/nutritionWidget.json";

        // Combine the base URL, endpoint, and API key to form the full URL
        return BASE_URL + endpoint + "?apiKey=" + API_KEY;
    }
}

