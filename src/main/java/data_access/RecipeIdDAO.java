package data_access;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Data Access Object (DAO) for retrieving recipe IDs by recipe name.
 * This class communicates with the Spoonacular API's "complexSearch" endpoint
 * to find recipe IDs based on a given recipe name.
 */
public class RecipeIdDAO {

    // API key for authenticating requests to the Spoonacular API
    private static final String API_KEY = AppConstants.API_KEY; //

    // Base URL for the Spoonacular "complexSearch" endpoint
    private static final String BASE_URL = "https://api.spoonacular.com/recipes/complexSearch";

    /**
     * Fetches the recipe ID for a given recipe name by querying the Spoonacular API.
     * @param recipeName The name of the recipe to search for.
     * @return The ID of the first recipe returned by the API, or -1 if no recipe is found or an error occurs.
     */
    public static int getRecipeIdByName(String recipeName) {
        try {
            // Construct the full API URL with the recipe name and API key
            String apiUrl = BASE_URL + "?query=" + recipeName.replace(" ", "+") + "&apiKey=" + API_KEY;

            // Create a URL object and open an HTTP connection
            URL url = new URL(apiUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            // Check the API response code
            int responseCode = connection.getResponseCode();
            if (responseCode != 200) {
                System.out.println("Error: API returned response code " + responseCode);
                return -1;
            }

            // Read the response from the API
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuilder response = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
            reader.close();

            // Parse the JSON response
            JSONObject jsonResponse = new JSONObject(response.toString());
            JSONArray results = jsonResponse.getJSONArray("results");

            if (results.length() == 0) {
                System.out.println("No recipes found for: " + recipeName);
                return -1;
            }

            // Get the ID of the first recipe in the results
            JSONObject firstResult = results.getJSONObject(0);
            int recipeId = firstResult.getInt("id");
            System.out.println("Recipe ID for " + recipeName + ": " + recipeId);

            return recipeId;
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }
}
