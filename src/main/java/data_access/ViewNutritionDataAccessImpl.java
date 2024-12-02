package data_access;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import entity.Nutrition;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import use_case.ViewNutritionDataAccessInterface;

public class ViewNutritionDataAccessImpl implements ViewNutritionDataAccessInterface {
    private static final String BASE_URL = "https://api.spoonacular.com";
    private static final String API_KEY = AppConstants.API_KEY;

    @Override
    public List<Nutrition> getNutritionDataForRecipe(int recipeId) {
        final OkHttpClient client = new OkHttpClient();

        final String endpoint = "/recipes/" + recipeId + "/nutritionWidget.json";
        final String url = BASE_URL + endpoint + "?apiKey=" + API_KEY;

        final Request request = new Request.Builder().url(url).build();

        final List<Nutrition> nutritionList = new ArrayList<>();

        try (Response response = client.newCall(request).execute()) {
            if (response.isSuccessful() && response.body() != null) {

                final String jsonResponse = response.body().string();
                final JSONObject jsonObject = new JSONObject(jsonResponse);

                if (jsonObject.has("nutrients")) {
                    final JSONArray nutrients = jsonObject.getJSONArray("nutrients");
                    for (int i = 0; i < nutrients.length(); i++) {
                        final JSONObject nutrientJson = nutrients.getJSONObject(i);

                        final String name = nutrientJson.getString("name");
                        final double amount = nutrientJson.getDouble("amount");
                        final String unit = nutrientJson.getString("unit");
                        final double percentOfDailyNeeds = nutrientJson.optDouble("percentOfDailyNeeds", 0);

                        final Nutrition nutrition = new Nutrition(name, amount, unit, percentOfDailyNeeds);
                        nutritionList.add(nutrition);
                    }
                }
            }
            else {
                System.err.println("Request failed with code: " + response.code());
            }

        }
        catch (IOException exception) {
            exception.printStackTrace();
        }
        return nutritionList;
    }
}
