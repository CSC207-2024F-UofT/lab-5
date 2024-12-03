package data_access;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import org.json.JSONArray;
import org.json.JSONObject;

public class SpoonacularAPI {
    private static final String API_KEY = AppConstants.API_KEY;
    private static final String RESPONSE_METHOD = "GET";

    // Define pantry staples
    private final List<String> pantryStaples = Arrays.asList(
            "salt", "pepper", "olive oil", "vegetable oil", "sugar", "flour", "baking powder", "butter", "water"
    );

    // Fetch recipes based on the user's available ingredients
    public List<String> getRecipesContaining(List<String> availableIngredients) {
        final List<String> recipes = new ArrayList<>();
        try {
            final String ingredients = String.join(",", availableIngredients);

            // API call to fetch recipes based on available ingredients
            final String urlString = "https://api.spoonacular.com/recipes/findByIngredients?ingredients=" + ingredients
                    + "&number=10&ranking=2&apiKey=" + API_KEY;
            final URL url = new URL(urlString);
            final HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod(RESPONSE_METHOD);

            final Scanner scanner = new Scanner(conn.getInputStream());
            final StringBuilder jsonResponse = new StringBuilder();
            while (scanner.hasNext()) {
                jsonResponse.append(scanner.nextLine());
            }
            scanner.close();

            // Parse the JSON response
            final JSONArray jsonArray = new JSONArray(jsonResponse.toString());
            for (int i = 0; i < jsonArray.length(); i++) {
                final JSONObject recipe = jsonArray.getJSONObject(i);

                // Get the missed ingredients for the recipe
                final JSONArray missedIngredients = recipe.getJSONArray("missedIngredients");

                // Count how many ingredients are truly missing
                int missingCount = 0;
                for (int j = 0; j < missedIngredients.length(); j++) {
                    final String ingredientName = missedIngredients.getJSONObject(j).getString("name").toLowerCase();
                    if (!availableIngredients.contains(ingredientName)) {
                        missingCount++;
                    }
                }

                // Include only recipes with 2 or fewer additional (missing) ingredients
                if (missingCount <= 2) {
                    recipes.add(recipe.getString("title"));
                }
            }
        }
        catch (Exception exception) {
            System.err.println("Error fetching recipes: " + exception.getMessage());
        }
        return recipes;
    }

    // Fetch missing ingredients for selected recipes
    public List<String> getMissingIngredients(List<String> selectedRecipes, List<String> availableIngredients) {
        final List<String> shoppingList = new ArrayList<>();
        try {
            for (String recipeTitle : selectedRecipes) {
                final String searchUrl = "https://api.spoonacular.com/recipes/complexSearch?query=" + recipeTitle
                        + "&apiKey=" + API_KEY;
                final URL searchRequest = new URL(searchUrl);
                final HttpURLConnection searchConn = (HttpURLConnection) searchRequest.openConnection();
                searchConn.setRequestMethod(RESPONSE_METHOD);

                final Scanner searchScanner = new Scanner(searchConn.getInputStream());
                final StringBuilder searchResponse = new StringBuilder();
                while (searchScanner.hasNext()) {
                    searchResponse.append(searchScanner.nextLine());
                }
                searchScanner.close();

                final JSONObject searchResult = new JSONObject(searchResponse.toString());
                final JSONArray results = searchResult.getJSONArray("results");

                if (!results.isEmpty()) {
                    final JSONObject recipe = results.getJSONObject(0);
                    final int id = recipe.getInt("id");

                    // Fetch ingredients for the recipe using its ID
                    final String ingredientUrl = "https://api.spoonacular.com/recipes/" + id
                            + "/information?includeNutrition=false&apiKey=" + API_KEY;
                    final URL ingredientRequest = new URL(ingredientUrl);
                    final HttpURLConnection ingredientConn = (HttpURLConnection) ingredientRequest.openConnection();
                    ingredientConn.setRequestMethod(RESPONSE_METHOD);

                    final Scanner ingredientScanner = new Scanner(ingredientConn.getInputStream());
                    final StringBuilder ingredientResponse = new StringBuilder();
                    while (ingredientScanner.hasNext()) {
                        ingredientResponse.append(ingredientScanner.nextLine());
                    }
                    ingredientScanner.close();

                    final JSONObject ingredientJson = new JSONObject(ingredientResponse.toString());
                    final JSONArray extendedIngredients = ingredientJson.getJSONArray("extendedIngredients");

                    // Include missing ingredients that are not in the user's list and not pantry staples
                    for (int i = 0; i < extendedIngredients.length(); i++) {
                        final JSONObject ingredient = extendedIngredients.getJSONObject(i);
                        final String ingredientName = ingredient.getString("original").toLowerCase();

                        // Check if the ingredient is not already available and not a pantry staple (substring match)
                        if (!availableIngredients.contains(ingredientName) && !containsPantryStaple(ingredientName)) {
                            shoppingList.add(ingredient.getString("original"));
                        }
                    }
                }
            }
        }
        catch (Exception exception) {
            System.err.println("Error fetching missing ingredients: " + exception.getMessage());
        }
        return shoppingList;
    }

    // Helper method to check if an ingredient contains a pantry staple
    private boolean containsPantryStaple(String ingredient) {
        for (String staple : pantryStaples) {
            if (ingredient.contains(staple)) {
                return true;
            }
        }
        return false;
    }

}
