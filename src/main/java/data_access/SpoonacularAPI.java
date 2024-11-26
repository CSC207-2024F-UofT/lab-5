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
    private final String apiKey = "38143f425a9f4698bd2e63550825a4ce";

    // Define pantry staples
    private final List<String> pantryStaples = Arrays.asList(
            "salt", "pepper", "olive oil", "vegetable oil", "sugar", "flour", "baking powder", "butter", "water"
    );

    // Fetch recipes based on the user's available ingredients
    public List<String> getRecipesContaining(List<String> availableIngredients) {
        List<String> recipes = new ArrayList<>();
        try {
            String ingredients = String.join(",", availableIngredients);

            // API call to fetch recipes based on available ingredients
            String urlString = "https://api.spoonacular.com/recipes/findByIngredients?ingredients=" + ingredients
                    + "&number=10&ranking=2&apiKey=" + apiKey;
            URL url = new URL(urlString);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

            Scanner scanner = new Scanner(conn.getInputStream());
            StringBuilder jsonResponse = new StringBuilder();
            while (scanner.hasNext()) {
                jsonResponse.append(scanner.nextLine());
            }
            scanner.close();

            // Parse the JSON response
            JSONArray jsonArray = new JSONArray(jsonResponse.toString());
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject recipe = jsonArray.getJSONObject(i);

                // Get the missed ingredients for the recipe
                JSONArray missedIngredients = recipe.getJSONArray("missedIngredients");

                // Count how many ingredients are truly missing
                int missingCount = 0;
                for (int j = 0; j < missedIngredients.length(); j++) {
                    String ingredientName = missedIngredients.getJSONObject(j).getString("name").toLowerCase();
                    if (!availableIngredients.contains(ingredientName)) {
                        missingCount++;
                    }
                }

                // Include only recipes with 2 or fewer additional (missing) ingredients
                if (missingCount <= 2) {
                    recipes.add(recipe.getString("title"));
                }
            }
        } catch (Exception e) {
            System.err.println("Error fetching recipes: " + e.getMessage());
        }
        return recipes;
    }

    // Fetch missing ingredients for selected recipes
    public List<String> getMissingIngredients(List<String> selectedRecipes, List<String> availableIngredients) {
        List<String> shoppingList = new ArrayList<>();
        try {
            for (String recipeTitle : selectedRecipes) {
                String searchUrl = "https://api.spoonacular.com/recipes/complexSearch?query=" + recipeTitle
                        + "&apiKey=" + apiKey;
                URL searchRequest = new URL(searchUrl);
                HttpURLConnection searchConn = (HttpURLConnection) searchRequest.openConnection();
                searchConn.setRequestMethod("GET");

                Scanner searchScanner = new Scanner(searchConn.getInputStream());
                StringBuilder searchResponse = new StringBuilder();
                while (searchScanner.hasNext()) {
                    searchResponse.append(searchScanner.nextLine());
                }
                searchScanner.close();

                JSONObject searchResult = new JSONObject(searchResponse.toString());
                JSONArray results = searchResult.getJSONArray("results");

                if (results.length() > 0) {
                    JSONObject recipe = results.getJSONObject(0);
                    int id = recipe.getInt("id");

                    // Fetch ingredients for the recipe using its ID
                    String ingredientUrl = "https://api.spoonacular.com/recipes/" + id + "/information?includeNutrition=false&apiKey=" + apiKey;
                    URL ingredientRequest = new URL(ingredientUrl);
                    HttpURLConnection ingredientConn = (HttpURLConnection) ingredientRequest.openConnection();
                    ingredientConn.setRequestMethod("GET");

                    Scanner ingredientScanner = new Scanner(ingredientConn.getInputStream());
                    StringBuilder ingredientResponse = new StringBuilder();
                    while (ingredientScanner.hasNext()) {
                        ingredientResponse.append(ingredientScanner.nextLine());
                    }
                    ingredientScanner.close();

                    JSONObject ingredientJson = new JSONObject(ingredientResponse.toString());
                    JSONArray extendedIngredients = ingredientJson.getJSONArray("extendedIngredients");

                    // Include missing ingredients that are not in the user's list and not pantry staples
                    for (int i = 0; i < extendedIngredients.length(); i++) {
                        JSONObject ingredient = extendedIngredients.getJSONObject(i);
                        String ingredientName = ingredient.getString("original").toLowerCase();

                        // Check if the ingredient is not already available and not a pantry staple (substring match)
                        if (!availableIngredients.contains(ingredientName) && !containsPantryStaple(ingredientName)) {
                            shoppingList.add(ingredient.getString("original"));
                        }
                    }
                }
            }
        } catch (Exception e) {
            System.err.println("Error fetching missing ingredients: " + e.getMessage());
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
