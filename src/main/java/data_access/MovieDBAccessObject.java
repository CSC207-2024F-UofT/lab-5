package data_access;


import okhttp3.*;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class MovieDBAccessObject {
    private static final String API_KEY = "b1f9d6a6a9884c948fd5e17a9a87171c";  // replace with your actual API key
    private static final String BASE_URL = "https://api.themoviedb.org/3/discover/movie";
    // Defining some constants.
    private static final String CONTENT_TYPE = "Content-Type";
    private static final String APPLICATION_JSON = "application/json";
    private static final String STATUS_CODE = "status_code";
    private static final String GRADE = "grade";
    private static final String MESSAGE = "message";
    private static final String NAME = "name";
    private static final String TOKEN = "token";
    // load getPassword() from env variable.
    private static final int SUCCESS_CODE = 200;

    public static void main(String[] args) {
        MovieDBAccessObject client = new MovieDBAccessObject();
//        int totalPages = 10;  // Set this to the number of pages you want to retrieve
//
//        for (int page = 1; page <= totalPages; page++) {
//            client.fetchMovies(page);
//        }
        client.getMatchingMovieNames("Dune: Prophecy");
    }

    public void fetchMovies(int page) {
        try {
            // Create the URL with pagination and API key as query parameters
            URL url = new URL(BASE_URL + "?api_key=" + API_KEY + "&page=" + page);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            // Check the response code
            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String inputLine;
                StringBuilder content = new StringBuilder();
                while ((inputLine = in.readLine()) != null) {
                    content.append(inputLine);
                }
                // Close connections
                in.close();
                connection.disconnect();

                // Print or process the response content
                System.out.println("Page " + page + ": " + content.toString());

            } else {
                System.out.println("Request failed. Response Code: " + responseCode);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void getMatchingMovieNames(String moviename){
        final OkHttpClient client = new OkHttpClient().newBuilder()
                .build();

        Request request = new Request.Builder()
                .url("https://api.themoviedb.org/3/search/movie?query=" + moviename + "&include_adult=false&language=en-US&page=1")
                .get()
                .addHeader("accept", "application/json")
                .addHeader("Authorization", "Bearer " + API_KEY)
                .build();

        try {
            final Response response = client.newCall(request).execute();
            final JSONObject responseBody = new JSONObject(response.body().string());

            if (responseBody.getInt(STATUS_CODE) != SUCCESS_CODE) {
                throw new RuntimeException(responseBody.getString(MESSAGE));
            }
            else {
                final JSONObject matches = responseBody.getJSONObject("results");
                System.out.println("Matches " + matches.toString());
            }
        }
        catch (IOException | JSONException event) {
            throw new RuntimeException(event);
        }

    }
}
