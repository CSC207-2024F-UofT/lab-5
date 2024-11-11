package data_access;

import entity.Recipe;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class SpoonacularRecipeDAO implements RecipeDAO {
    private static final String API_KEY = "5d06ae7be18746aaa4d1f1e5877f4450"; // Replace with your Spoonacular API key
    private static final String BASE_URL = "https://api.spoonacular.com";
    private final OkHttpClient client;

    public SpoonacularRecipeDAO() {
        this.client = new OkHttpClient();
    }

    @Override
    public List<Recipe> getAllRecipes() {
        // If you need to fetch all recipes, you could use a default search (e.g., return popular recipes)
        return getRecipesByIngredients(List.of("pasta")); // Example default search
    }

    @Override
    public List<Recipe> getRecipesByIngredients(List<String> ingredients) {
        // Convert ingredients list to a comma-separated string
        String ingredientsQuery = ingredients.stream()
                .map(String::trim)
                .collect(Collectors.joining(","));

        // Construct the URL with the ingredients query
        String endpoint = "/recipes/findByIngredients";
        String url = BASE_URL + endpoint + "?apiKey=" + API_KEY + "&ingredients=" + ingredientsQuery + "&number=5";

        // Build the request
        Request request = new Request.Builder()
                .url(url)
                .build();

        List<Recipe> recipes = new ArrayList<>();
        try (Response response = client.newCall(request).execute()) {
            if (response.isSuccessful() && response.body() != null) {
                // Parse the JSON response
                String jsonResponse = response.body().string();
                JSONArray results = new JSONArray(jsonResponse);

                // Loop through the results and create Recipe objects
                for (int i = 0; i < results.length(); i++) {
                    JSONObject recipeJson = results.getJSONObject(i);
                    String title = recipeJson.getString("title");
                    String recipeUrl = BASE_URL + "/recipes/" + recipeJson.getInt("id") + "/information"; // URL to recipe details
                    JSONArray ingredientsJson = recipeJson.getJSONArray("usedIngredients");

                    // Collect ingredients from the JSON response
                    List<String> recipeIngredients = new ArrayList<>();
                    for (int j = 0; j < ingredientsJson.length(); j++) {
                        recipeIngredients.add(ingredientsJson.getJSONObject(j).getString("name"));
                    }

                    // Create and add Recipe object to the list
                    Recipe recipe = new Recipe(title, recipeUrl, recipeIngredients);
                    recipes.add(recipe);
                }
            } else {
                System.out.println("Request failed with code: " + response.code());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return recipes;
    }
}
