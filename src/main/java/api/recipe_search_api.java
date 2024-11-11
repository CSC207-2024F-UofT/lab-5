package api;

import entity.Recipe;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.*;

public class recipe_search_api {

    private static final String API_URL = "https://api.edamam.com/api/recipes/v2";
    private static final String APP_ID = "bb181cd2";
    private static final String APP_KEY = "bf09ce656684790d61f32c328f1e720f";

    private final Runnable customTask;

    // Constructor accepting a Runnable
    public recipe_search_api(Runnable customTask) {
        this.customTask = customTask;
    }

    // Default constructor for compatibility
    public recipe_search_api() {
        this.customTask = null;
    }

    public List<Recipe> searchRecipebyName(String q) {
        final OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        final Request request = new Request.Builder()
                .url(String.format("%s?type=public&q=%s&app_id=%s&app_key=%s", API_URL, q, APP_ID, APP_KEY))
                .build();

        try {
            Response response = client.newCall(request).execute();

            // Check response
            if (!response.isSuccessful()) {
                throw new IOException("Unexpected code: " + response);
            }

            String responseBody = response.body().string();
            JSONObject root = new JSONObject(responseBody);

            JSONArray hits = root.getJSONArray("hits");
            List<Recipe> recipes = new ArrayList<>();

            for (int i = 0; i < hits.length(); i++) {
                JSONObject recipeObj = hits.getJSONObject(i).getJSONObject("recipe");

                // Extract recipe details
                String name = recipeObj.getString("label");
                int servings = recipeObj.optInt("yield", 1);
                int calories = (int) Math.round(recipeObj.getDouble("calories") / servings);

                // Extract nutrients
                Map<String, Integer> nutrients = new HashMap<>();
                JSONObject totalNutrients = recipeObj.getJSONObject("totalNutrients");
                for (String key : totalNutrients.keySet()) {
                    JSONObject nutrient = totalNutrients.getJSONObject(key);
                    nutrients.put(nutrient.getString("label"), (int) nutrient.getDouble("quantity"));
                }

                // Extract tags
                Set<String> tags = new HashSet<>();
                JSONArray healthLabels = recipeObj.getJSONArray("healthLabels");
                for (int j = 0; j < healthLabels.length(); j++) {
                    tags.add(healthLabels.getString(j));
                }

                // Build the Recipe object
                Recipe recipe = Recipe.builder()
                        .name(name)
                        .servings(servings)
                        .calories(calories)
                        .nutrients(nutrients)
                        .tags(tags)
                        .build();

                recipes.add(recipe);
            }

            return recipes;
        } catch (IOException | JSONException event) {
            throw new RuntimeException(event);
        }
    }

    public static void main(String[] args) {
        // Example: Pass a custom void task to the constructor
        Runnable task = () -> System.out.println("Custom task is running...");

        // Use the constructor with the custom task
        recipe_search_api apiWithTask = new recipe_search_api(task);

        // Run the custom task
        if (apiWithTask.customTask != null) {
            apiWithTask.customTask.run();
        }

        // Default usage without a task
        recipe_search_api api = new recipe_search_api();
        List<Recipe> recipes = api.searchRecipebyName("burger");

        for (Recipe recipe : recipes) {
            System.out.println("Name: " + recipe.getName());
            System.out.println("Servings: " + recipe.getServings());
            System.out.println("Calories: " + recipe.getCalories());
            System.out.println("Nutrients: " + recipe.getNutrients());
            System.out.println("Tags: " + recipe.getTags());
            System.out.println("-----------------------------");
        }
    }
}
