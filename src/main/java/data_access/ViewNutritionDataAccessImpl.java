package data_access;

import entity.Nutrition;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.json.JSONArray;
import org.json.JSONObject;
import use_case.ViewNutritionDataAccessInterface;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ViewNutritionDataAccessImpl implements ViewNutritionDataAccessInterface {
    private static final String BASE_URL = "https://api.spoonacular.com";
    private static final String API_KEY = "5d06ae7be18746aaa4d1f1e5877f4450";

    @Override
    public List<Nutrition> getNutritionDataForRecipe(int recipeId) {
        OkHttpClient client = new OkHttpClient();

        // Define the endpoint and query parameters(None)
        String endpoint = "/recipes/" + recipeId + "/nutritionWidget.json";
        String url = BASE_URL + endpoint + "?apiKey=" + API_KEY;

        // Build the request
        Request request = new Request.Builder().url(url).build();

        List<Nutrition> nutritionList = new ArrayList<>();

        try (Response response = client.newCall(request).execute()) {
            if (response.isSuccessful() && response.body() != null) {

                // Parse the JSON response
                String jsonResponse = response.body().string();
                JSONObject jsonObject = new JSONObject(jsonResponse);

                // Extract 'nutrients' array
                if (jsonObject.has("nutrients")) {
                    JSONArray nutrients = jsonObject.getJSONArray("nutrients");
                    for (int i = 0; i < nutrients.length(); i++) {
                        JSONObject nutrientJson = nutrients.getJSONObject(i);

                        String name = nutrientJson.getString("name");
                        double amount = nutrientJson.getDouble("amount");
                        String unit = nutrientJson.getString("unit");
                        double percentOfDailyNeeds = nutrientJson.optDouble("percentOfDailyNeeds", 0);

                        Nutrition nutrition = new Nutrition(name, amount, unit, percentOfDailyNeeds);
                        nutritionList.add(nutrition);
                    }
                }
                return nutritionList;
            } else {
                System.err.println("Request failed with code: " + response.code());
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return nutritionList;
    }
}
