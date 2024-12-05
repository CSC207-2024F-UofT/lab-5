package data_access;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;

import entity.CommonMovie;
import entity.Movie;
import okhttp3.*;

/**
 * The methods to access the movie API.
 */
public class APIMovieAccess {
    private static final String API_KEY = "b1f9d6a6a9884c948fd5e17a9a87171c";
    private static final String BASE_URL = "https://api.themoviedb.org/3";
    private static final int NUM_MOVIE = 3;
    // private static final String SEARCH_ENDPOINT = "search/movie";
    // private static final String LANGUAGE = "en-US";

    // Genre ID constants
    private static final int ACTION = 28;
    private static final int ADVENTURE = 12;
    private static final int ANIMATION = 16;
    private static final int COMEDY = 35;
    private static final int CRIME = 80;
    private static final int DOCUMENTARY = 99;
    private static final int DRAMA = 18;
    private static final int FAMILY = 10751;
    private static final int FANTASY = 14;
    private static final int HISTORY = 36;
    private static final int HORROR = 27;
    private static final int MUSIC = 10402;
    private static final int MYSTERY = 9648;
    private static final int ROMANCE = 10749;
    private static final int SCI_FI = 878;
    private static final int TV_MOVIE = 10770;
    private static final int THRILLER = 53;
    private static final int WAR = 10752;
    private static final int WESTERN = 37;

    // Hashmap for the ID's and the related genres
    private static final Map<Integer, String> GENRE_MAP = new HashMap<>();

    static {
        GENRE_MAP.put(ACTION, "Action");
        GENRE_MAP.put(ADVENTURE, "Adventure");
        GENRE_MAP.put(ANIMATION, "Animation");
        GENRE_MAP.put(COMEDY, "Comedy");
        GENRE_MAP.put(CRIME, "Crime");
        GENRE_MAP.put(DOCUMENTARY, "Documentary");
        GENRE_MAP.put(DRAMA, "Drama");
        GENRE_MAP.put(FAMILY, "Family");
        GENRE_MAP.put(FANTASY, "Fantasy");
        GENRE_MAP.put(HISTORY, "History");
        GENRE_MAP.put(HORROR, "Horror");
        GENRE_MAP.put(MUSIC, "Music");
        GENRE_MAP.put(MYSTERY, "Mystery");
        GENRE_MAP.put(ROMANCE, "Romance");
        GENRE_MAP.put(SCI_FI, "Science Fiction");
        GENRE_MAP.put(TV_MOVIE, "TV Movie");
        GENRE_MAP.put(THRILLER, "Thriller");
        GENRE_MAP.put(WAR, "War");
        GENRE_MAP.put(WESTERN, "Western");
    }

    /**
     * The search function that finds all possible movies with a vague title.
     *
     * @param query the search query that the user inputs.
     * @return a string containing all the search results or an empty string if there are no search results
     */
    public static String searchMovie(String query) {
        try {
            // Construct the full URL
            final String urlString = BASE_URL + "/search/movie?api_key=" + API_KEY + "&query=" + query.replace(" ", "%20");
            final URL url = new URL(urlString);

            // Open the connection
            final HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            // Check the response code and throw an IOException if it's not successful
            final int responseCode = connection.getResponseCode();
            if (responseCode != HttpURLConnection.HTTP_OK) {
                throw new IOException("HTTP request failed with response code: " + responseCode);
            }

            // Use try-with-resources to ensure the BufferedReader is closed properly
            try (BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
                String inputLine;
                final StringBuilder response = new StringBuilder();
                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                // Return the response as a string
                return response.toString();
            }
        }
        catch (IOException noResultFound) {
            // Log the error if needed and return an empty string
            return "";
        }
    }

    /**
     * The filtered version of the search function which returns a list of info for 3 movies.
     *
     * @param query the search query that the user inputs.
     * @return list of strings of the data
     */
    public static List<CommonMovie> formatedSearchedMovies(String query) {
        final String responseString = searchMovie(query);

        final List<CommonMovie> searchResults = new ArrayList<>();

        // Parse the JSON response string into a JSONObject
        final JSONObject jsonObject = new JSONObject(responseString);

        // Get results array
        final JSONArray resultsArray = jsonObject.getJSONArray("results");

        // Iterate through the first 3 three movies
        for (int i = 0; i < NUM_MOVIE; i++) {
            if (!resultsArray.isEmpty()) {

                final JSONObject movie = resultsArray.getJSONObject(i);

                // Extract movie details
                final int movieId = movie.getInt("id");
                final String title = movie.getString("title");
                final String posterPath = movie.getString("poster_path");
                final String overview = movie.getString("overview");
                final String voteAverage = String.valueOf(movie.getDouble("vote_average"));

                // get the genre IDs in an array
                final JSONArray genreID = movie.getJSONArray("genre_ids");

                // get the associated genres in a list using the Hashmap
                final List<String> genreList = new ArrayList<>();

                for (int j = 0; j < genreID.length(); j++) {
                    final int genreNum = genreID.getInt(j);
                    final String genre = GENRE_MAP.get(genreNum);
                    genreList.add(genre);
                }

                // create a movie with the title, and update the information
                final CommonMovie result = new CommonMovie(title);

                result.setInformation(posterPath, overview, voteAverage, genreList, movieId);

                searchResults.add(result);
            }

        }
        // return the final list
        return searchResults;
    }

    /**
     * The filtered version of the search function which returns a list of info for 3 movies.
     *
     * @param query the search query that the user inputs.
     * @param id id
     * @return list of strings of the data
     */
    public Movie searchByID(String query, int id) {
        final String responseString = searchMovie(query);

        final CommonMovie result = new CommonMovie(query);

        // Parse the JSON response string into a JSONObject
        final JSONObject jsonObject = new JSONObject(responseString);

        // Get results array
        final JSONArray resultsArray = jsonObject.getJSONArray("results");

        // Iterate through the first 3 three movies
        for (int i = 0; i < resultsArray.length(); i++) {
            final JSONObject movie = resultsArray.getJSONObject(i);

            // Extract movie details
            final int movieId = movie.getInt("id");
            if (movieId == id) {
                final String posterPath = movie.getString("poster_path");
                final String overview = movie.getString("overview");
                final String voteAverage = String.valueOf(movie.getDouble("vote_average"));
                // get the genre IDs in an array
                final JSONArray genreID = movie.getJSONArray("genre_ids");
                // get the associated genres in a list using the Hashmap
                final List<String> genreList = new ArrayList<>();
                for (int j = 0; j < genreID.length(); j++) {
                    final int genreNum = genreID.getInt(j);
                    final String genre = GENRE_MAP.get(genreNum);
                    genreList.add(genre);
                }
                // create a movie with the title, and update the information
                result.setInformation(posterPath, overview, voteAverage, genreList, movieId);
            }
        }
        // return the final list
        return result;
    }

    /**
     * The main method to test the TMDbClient.
     *
     * @param args is the provided argument
     * @throws Exception if something bad happens
     */
    public static void main(String[] args) throws Exception {
        final String responseString = APIMovieAccess.getMovieGenresAsJson();
        System.out.println(responseString);
    }

    /**
     * Retrieves a string of movie genres from the API.
     *
     * @return a string where the key is the genre ID and the value is the genre name
     * @throws AssertionError if the response body is null
     */
    public static String getMovieGenresAsJson() {
        final OkHttpClient client = new OkHttpClient();

        // Create the request
        final Request request = new Request.Builder()
                .url("https://api.themoviedb.org/3/genre/movie/list?language=en&api_key=" + API_KEY)
                .get()
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                throw new IOException("Unexpected response code: " + response.code());
            }
            if (response.body() == null) {
                throw new AssertionError();
            }
            return response.body().string();
        }
        catch (IOException exception) {
            exception.printStackTrace();
            return null;
        }
    }
}

