import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.json.JSONArray;
import org.json.JSONObject;
import java.io.IOException;

public class SpoonacularExample {
    private static final String API_KEY = "5d06ae7be18746aaa4d1f1e5877f4450";
    private static final String BASE_URL = "https://api.spoonacular.com";

    public static void main(String[] args) {
        final OkHttpClient client = new OkHttpClient();

        // Define the endpoint and query parameters
        final String endpoint = "/recipes/complexSearch";
        // This is where we specify the query (ingredients we have, number of recipes we want, cuisine, etc.)
        final String url = BASE_URL + endpoint + "?apiKey=" + API_KEY + "&query=pork&number=5&cuisine=chinese";

        // Build the request
        final Request request = new Request.Builder()
                .url(url)
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (response.isSuccessful() && response.body() != null) {
                // Parse the JSON response
                final String jsonResponse = response.body().string();
                final JSONObject jsonObject = new JSONObject(jsonResponse);
                final JSONArray results = jsonObject.getJSONArray("results");

                // Loop through the results and print recipe titles and IDs
                for (int i = 0; i < results.length(); i++) {
                    final JSONObject recipe = results.getJSONObject(i);
                }
            }
            else {
                System.out.println("Request failed with code: " + response.code());
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
