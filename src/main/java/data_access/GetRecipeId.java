package data_access;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.json.JSONArray;
import org.json.JSONObject;

public class GetRecipeId {

    private static final String API_KEY = AppConstants.API_KEY;
    private static final String BASE_URL = "https://api.spoonacular.com/recipes/complexSearch";

    public static int getRecipeIdByName(String recipeName) {
        try {
            final String apiUrl = BASE_URL + "?query=" + recipeName.replace(" ", "+") + "&apiKey=" + API_KEY;

            final URL url = new URL(apiUrl);
            final HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            final int responseCode = connection.getResponseCode();
            if (responseCode != AppConstants.RESPONSE_CODE_200) {
                System.out.println("Error: API returned response code " + responseCode);
                return -1;
            }

            final BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            final StringBuilder response = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
            reader.close();

            final JSONObject jsonResponse = new JSONObject(response.toString());
            final JSONArray results = jsonResponse.getJSONArray("results");

            if (results.isEmpty()) {
                System.out.println("No recipes found for: " + recipeName);
                return -1;
            }

            final JSONObject firstResult = results.getJSONObject(0);
            final int recipeId = firstResult.getInt("id");
            System.out.println("Recipe ID for " + recipeName + ": " + recipeId);

            return recipeId;
        }
        catch (Exception event) {
            event.printStackTrace();
            return -1;
        }
    }
}
