import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;

public class SpoonacularExample {
    private static final String API_KEY = "5d06ae7be18746aaa4d1f1e5877f4450"; // Replace with your Spoonacular API key
    private static final String BASE_URL = "https://api.spoonacular.com";

    public static void main(String[] args) {
        OkHttpClient client = new OkHttpClient();

        // Define the endpoint and query parameters
        String endpoint = "/recipes/complexSearch";
        String url = BASE_URL + endpoint + "?apiKey=" + API_KEY + "&query=pasta&number=2";

        // Build the request
        Request request = new Request.Builder()
                .url(url)
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (response.isSuccessful() && response.body() != null) {
                // Parse the JSON response
                String jsonResponse = response.body().string();
                JSONObject jsonObject = new JSONObject(jsonResponse);
                JSONArray results = jsonObject.getJSONArray("results");

                // Loop through the results and print recipe titles and IDs
                for (int i = 0; i < results.length(); i++) {
                    JSONObject recipe = results.getJSONObject(i);
                    System.out.println("Recipe: " + recipe.getString("title"));
                    System.out.println("ID: " + recipe.getInt("id"));
                }
            } else {
                System.out.println("Request failed with code: " + response.code());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
