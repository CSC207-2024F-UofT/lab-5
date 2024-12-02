package data_access;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import use_case.nutrition_information.NutritionInformationAccessInterface;

import java.io.IOException;

public class NutritionInformationDAO implements NutritionInformationAccessInterface {
    private static final String BASE_URL = "https://api.spoonacular.com";
    private static final String API_KEY = "5fcf2eef76af4e6893959ceefae0a087";

    @Override
    public String fetchNutritionInformation(int recipeId) {
        OkHttpClient client = new OkHttpClient();

        String url = buildUrl(recipeId);

        Request request = new Request.Builder().url(url).build();

        try (Response response = client.newCall(request).execute()) {
            if (response.isSuccessful() && response.body() != null) {
                return response.body().string();
            } else {
                throw new IOException("Failed to fetch data: HTTP code " + response.code());
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private String buildUrl(int recipeId) {
        String endpoint = "/recipes/" + recipeId + "/nutritionWidget.json";
        return BASE_URL + endpoint + "?apiKey=" + API_KEY;
    }
}

