package data_access;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import entity.Recipe;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import use_case.recipe_search.RecipeSearchDataAccessInterface;

/**
 * The DAO for recipe data.
 */
public class RecipeDataAccessObject implements RecipeSearchDataAccessInterface {

    private static final String API_URL = "https://api.edamam.com/api/recipes/v2";
    private static final String APP_ID = "bb181cd2";
    private static final String APP_KEY = "bf09ce656684790d61f32c328f1e720f";

    private final Runnable customTask;

    // Default constructor for compatibility
    public RecipeDataAccessObject() {
        this.customTask = null;
    }

    private String buildRangeQuery(String key, String min, String max) {
        String val = "";
        if (!"".equals(min) && !"".equals(max)) {
            val = String.format("&%s=%s-%s", key, min, max);
        }
        else if (!"".equals(min)) {
            val = String.format("&%s=%s%%2B", key, min);
        }
        else if (!"".equals(max)) {
            val = String.format("&%s=%s", key, max);
        }
        return val;
    }

    @Override
    public List<Recipe> searchRecipe(String q, String cal_min, String cal_max, String carb_min, String carb_max, String protein_min, String protein_max, String fat_min, String fat_max) {
        final OkHttpClient client = new OkHttpClient().newBuilder().build();

        // Build query parameters
        String newq = "";
        if (!"".equals(q)) {
            newq = "&q=" + q;
        }
        final String calories = buildRangeQuery("calories", cal_min, cal_max);
        final String carbs = buildRangeQuery("nutrients%5BCHOCDF%5D", carb_min, carb_max);
        final String protein = buildRangeQuery("nutrients%5BPROCNT%5D", protein_min, protein_max);
        final String fat = buildRangeQuery("nutrients%5BFAT%5D", fat_min, fat_max);

        final Request request = new Request.Builder()
                .url(String.format("%s?type=public%s&app_id=%s&app_key=%s%s%s%s%s",
                        API_URL, newq, APP_ID, APP_KEY, calories, carbs, protein, fat))
                .build();

        try {
            final Response response = client.newCall(request).execute();

            // Check response
            if (!response.isSuccessful()) {
                throw new IOException("Unexpected code: " + response);
            }

            final String responseBody = response.body().string();
            final JSONObject root = new JSONObject(responseBody);

            final JSONArray hits = root.getJSONArray("hits");
            final List<Recipe> recipes = new ArrayList<>();

            for (int i = 0; i < hits.length(); i++) {
                final JSONObject recipeObj = hits.getJSONObject(i).getJSONObject("recipe");

                // Extract recipe details
                final String name = recipeObj.getString("label");
                final int servings = recipeObj.optInt("yield", 1);
                final int caloriesValue = (int) Math.round(recipeObj.getDouble("calories") / servings);
                final String url = recipeObj.getString("url");
                // final String image = recipeObj.getJSONObject("images").getJSONObject("THUMBNAIL").getString("url");

                // Extract nutrients
                final Map<String, Integer> nutrients = new HashMap<>();
                final JSONObject totalNutrients = recipeObj.getJSONObject("totalNutrients");
                for (String key : totalNutrients.keySet()) {
                    final JSONObject nutrient = totalNutrients.getJSONObject(key);
                    nutrients.put(nutrient.getString("label"), (int) nutrient.getDouble("quantity"));
                }

                //                // Extract tags
                //                final Set<String> tags = new HashSet<>();
                //                final JSONArray healthLabels = recipeObj.getJSONArray("healthLabels");
                //                for (int j = 0; j < healthLabels.length(); j++) {
                //                    tags.add(healthLabels.getString(j));
                //                }

                // Build the Recipe object
                final Recipe recipe = Recipe.builder()
                        .name(name)
                        .servings(servings)
                        .calories(caloriesValue)
                        .nutrients(nutrients)
                        // .tags(tags)
                        .url(url)
                        // .image(image)
                        .build();

                recipes.add(recipe);
            }

            return recipes;
        }
        catch (IOException | JSONException event) {
            throw new RuntimeException(event);
        }
    }
}
