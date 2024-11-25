package data_access;

import entity.Ingredient;
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

    private String ingredientsToString(List<String> ingredients) {
        // Convert ingredients list to a comma-separated string
        final String ingredientsQuery = ingredients.stream()
                .map(String::trim)
                .collect(Collectors.joining(","));
        return ingredientsQuery;
    }

    @Override
    public List<Recipe> getRecipesByIngredients(List<String> ingredients) {
        // Convert ingredients list to a comma-separated string
        // String ingredientsQuery = ingredients.stream()
        //         .map(String::trim)
        //         .collect(Collectors.joining(","));
        final String ingredientsQuery = ingredientsToString(ingredients);

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
                    List<Ingredient> recipeIngredients = new ArrayList<>();
                    for (int j = 0; j < ingredientsJson.length(); j++) {
                        recipeIngredients.add(new Ingredient(ingredientsJson.getJSONObject(j).getString("name")));
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

    /**
     * Also searches for recipes but with some extra filters.
     * @param ingredients list of string ingredients
     * @param diet diet choice obtained from user; if none selected, diet will be "Any"
     * @param cuisine cuisine choice obtained from user; if none selected, cuisine will be "Any"
     * @return a list of recipes that match filters
     */
    public List<Recipe> filterSearchRecipes(List<String> ingredients, String diet, String cuisine) {
        final String ingredientsQuery = ingredientsToString(ingredients);
        final String endpoint = BASE_URL + "/recipes/complexSearch";
        final StringBuilder urlBuilder = new StringBuilder(endpoint);
        urlBuilder.append("?apiKey=" + API_KEY);
        urlBuilder.append("&includeIngredients=").append(ingredientsQuery);

        if (!"Any".equals(diet)) {
            urlBuilder.append("&diet=").append(diet);
        }
        if (!"Any".equals(cuisine)) {
            urlBuilder.append("&cuisine=").append(cuisine);
        }

        // Build the request **REPEAT OF ABOVE SHOULD REFACTOR
        final Request request = new Request.Builder()
                .url(String.valueOf(urlBuilder))
                .build();

        final List<Recipe> recipes = new ArrayList<>();
        try (Response response = client.newCall(request).execute()) {
            if (response.isSuccessful() && response.body() != null) {
                // Parse the JSON response
                final String jsonResponse = response.body().string();
                final JSONArray results = new JSONArray(jsonResponse);

                // Loop through the results and create Recipe objects
                for (int i = 0; i < results.length(); i++) {
                    final JSONObject recipeJson = results.getJSONObject(i);
                    final String title = recipeJson.getString("title");
                    final String recipeUrl = BASE_URL + "/recipes/" + recipeJson.getInt("id") + "/information"; // URL to recipe details
                    final JSONArray ingredientsJson = recipeJson.getJSONArray("usedIngredients");

                    // Collect ingredients from the JSON response
                    final List<Ingredient> recipeIngredients = new ArrayList<>();
                    for (int j = 0; j < ingredientsJson.length(); j++) {
                        recipeIngredients.add(new Ingredient(ingredientsJson.getJSONObject(j).getString("name")));
                    }

                    // Create and add Recipe object to the list
                    final Recipe recipe = new Recipe(title, recipeUrl, recipeIngredients);
                    recipes.add(recipe);
                }
            }
            else {
                System.out.println("Request failed with code: " + response.code());
            }
        }
        catch (IOException exception) {
            exception.printStackTrace();
        }
        return recipes;

    }

    // hard-coded for now
    @Override
    public List<String> getAvailableDiets() {
        final List<String> diets = new ArrayList<>();
        diets.add("glutenfree");
        diets.add("vegetarian");
        diets.add("vegan");
        diets.add("ketogenic");
        diets.add("pescetarian");
        diets.add("paleo");
        return diets;
    }

    @Override
    public List<String> getAvailableCuisines() {
        final List<String> cuisines = new ArrayList<>();
        cuisines.add("african");
        cuisines.add("american");
        cuisines.add("caribbean");
        cuisines.add("chinese");
        cuisines.add("greek");
        cuisines.add("indian");
        cuisines.add("italian");
        cuisines.add("japanese");
        cuisines.add("korean");
        cuisines.add("mediterranean");
        cuisines.add("mexican");
        cuisines.add("middleeastern");
        cuisines.add("thai");
        return cuisines;
    }

}
