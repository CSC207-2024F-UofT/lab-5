package data_access;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import org.json.JSONArray;
import org.json.JSONObject;

public class RecipeIdDAO {

    private static final String API_KEY = "5fcf2eef76af4e6893959ceefae0a087"; //
    private static final String BASE_URL = "https://api.spoonacular.com/recipes/complexSearch";

    public static int getRecipeIdByName(String recipeName) {
        try {
            String apiUrl = BASE_URL + "?query=" + recipeName.replace(" ", "+") + "&apiKey=" + API_KEY;

            URL url = new URL(apiUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            int responseCode = connection.getResponseCode();
            if (responseCode != 200) {
                System.out.println("Error: API returned response code " + responseCode);
                return -1;
            }

            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuilder response = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
            reader.close();

            JSONObject jsonResponse = new JSONObject(response.toString());
            JSONArray results = jsonResponse.getJSONArray("results");

            if (results.length() == 0) {
                System.out.println("No recipes found for: " + recipeName);
                return -1;
            }

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
