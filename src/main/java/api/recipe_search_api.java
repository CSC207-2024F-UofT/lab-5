package api;

import entity.Recipe;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.List;

import static java.util.Collections.list;

public class recipe_search_api {


    private static final String API_URL = "";
    private static final String APP_ID = "bb181cd2";
    private static final String APP_KEY = "bf09ce656684790d61f32c328f1e720f";

    public Recipe[] searchRecipe(){
        final OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        final String requestUrl = "https://api.edamam.com/api/recipes/v2";
        final Request request = new Request.Builder().url(requestUrl.toString()).method("GET", null).addHeader("Content-Type", "application/json").build();

        try {
            final Response response = client.newCall(request).execute();
            final JSONObject responseBody = new JSONObject(response.body().string());
            final JSONArray recipes_array = responseBody.getJSONArray("recipes");
            final Recipe[] recipes = new Recipe[recipes_array.length()];
            for (int i = 0; i < recipes_array.length(); i++) {
                final JSONObject recipe = recipes_array.getJSONObject(i);
                recipes[i] = Recipe.builder()
                        .username(recipe.getString("username"))
                        .course(recipe.getString("course"))
                        .grade(recipe.getInt(GRADE))
                        .build();
            }
            return recipes;

        }
        catch (IOException | JSONException event) {
            throw new RuntimeException(event);
        }
    }
}
