package data_access;

import entity.Ingredient;
import entity.Recipe;
import entity.User;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserDAOImpl implements UserDAO {
    private static final String FILE_PATH = "users.json";
    private Map<String, User> usersDatabase;

    public UserDAOImpl() {
        usersDatabase = loadUsersFromFile();
    }

    @Override
    public boolean addUser(User user) {
        if (usersDatabase.containsKey(user.getUsername())) {
            System.out.println("User already exists: " + user.getUsername());
            return false; // User already exists
        }
        usersDatabase.put(user.getUsername(), user);
        saveUsersToFile();
        System.out.println("User added successfully: " + user.getUsername());
        return true;
    }

    @Override
    public User findUserByUsername(String username) {
        return usersDatabase.get(username); // Returns null if the user doesn't exist
    }


    @Override
    public boolean validateUser(String username, String password) {
        // Check for empty username or password
        if (username == null || username.trim().isEmpty() || password == null || password.trim().isEmpty()) {
            System.out.println("Empty username or password provided."); // Debugging output
            return false;
        }

        User user = findUserByUsername(username);
        if (user == null) {
            System.out.println("User not found: " + username); // Debugging output
            return false; // User doesn't exist
        } else if (!user.getPassword().equals(password)) {
            System.out.println("Incorrect password for user: " + username); // Debugging output
            return false; // Password doesn't match
        }
        System.out.println("User validated successfully: " + username); // Debugging output
        return true; // User exists and password matches
    }




    // Load users from JSON file using org.json
    private Map<String, User> loadUsersFromFile() {
        Map<String, User> users = new HashMap<>();
        try (FileReader reader = new FileReader(FILE_PATH)) {
            JSONArray usersArray = new JSONArray(new JSONTokener(reader));
            for (int i = 0; i < usersArray.length(); i++) {
                JSONObject userObject = usersArray.getJSONObject(i);
                String username = userObject.getString("username");
                String password = userObject.getString("password");

                // reconstructing bookmarks as a list of recipes
                JSONArray bookmarksArray = userObject.getJSONArray("bookmarks");
                List<Recipe> recipeBookmarks = new ArrayList<>();
                for (int k = 0; k < bookmarksArray.length(); k++) {
                    JSONObject recipeJson = bookmarksArray.getJSONObject(k);
                    JSONArray ingredientsJson = recipeJson.getJSONArray("ingredients");
                    List<Ingredient> ingredients = new ArrayList<>();
                    for (int j = 0; j < ingredientsJson.length(); j++) {
                        JSONObject ingredientJson = ingredientsJson.getJSONObject(j);
                        Ingredient ingredient = new Ingredient(ingredientJson.getString("name"));
                        ingredients.add(ingredient);
                    }
                    Recipe recipe = new Recipe(
                            recipeJson.getString("name"),
                            recipeJson.getString("url"),
                            ingredients,
                            recipeJson.getString("image"));
                    // ^ didn't add cuisineType and dietaryType
                    recipeBookmarks.add(recipe);
                }

                // reconstructing recentlyViewed as a list of recipes
                JSONArray recentlyViewed = userObject.getJSONArray("recentlyViewed");
                JSONArray recentlyViewedArray = userObject.getJSONArray("recentlyViewed");
                List<Recipe> recipeRecentlyViewed = new ArrayList<>();
                for (int m = 0; m < recentlyViewedArray.length(); m++) {
                    JSONObject recipeJson = recentlyViewedArray.getJSONObject(m);
                    JSONArray ingredientsJson = recipeJson.getJSONArray("ingredients");
                    List<Ingredient> ingredients = new ArrayList<>();
                    for (int j = 0; j < ingredientsJson.length(); j++) {
                        JSONObject ingredientJson = ingredientsJson.getJSONObject(j);
                        Ingredient ingredient = new Ingredient(ingredientJson.getString("name"));
                        ingredients.add(ingredient);
                    }
                    Recipe recipe = new Recipe(
                            recipeJson.getString("name"),
                            recipeJson.getString("url"),
                            ingredients,
                            recipeJson.getString("image"));
                    // ^ didn't add cuisineType and dietaryType
                    recipeRecentlyViewed.add(recipe);
                }

                // putting the new user object in the list of users to be returned
                users.put(username, new User(username, password, recipeBookmarks, recipeRecentlyViewed));
                System.out.println("Loaded user: " + username); // Debugging output
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return users;
    }


    // Save users to JSON file using org.json
    private void saveUsersToFile() {
        JSONArray usersArray = new JSONArray();
        for (User user : usersDatabase.values()) {
            JSONObject userObject = new JSONObject();
            userObject.put("username", user.getUsername());
            userObject.put("password", user.getPassword());
            userObject.put("bookmarks", user.getBookmarks());
            userObject.put("recentlyViewed", user.getRecentlyViewed());
            usersArray.put(userObject);
        }
        try (FileWriter writer = new FileWriter(FILE_PATH)) {
            writer.write(usersArray.toString(4)); // Indent for readability
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // TODO lots of duplicate code in the following methods...
    // Add a recipe to a user's list of bookmarks
    public void addBookmarkToFile(String username, Recipe recipe) {
        final Map<String, User> users = loadUsersFromFile();
        final User user = users.get(username);
        user.addBookmark(recipe);
        usersDatabase.put(user.getUsername(), user);
        saveUsersToFile();
        System.out.println("Bookmark added successfully for: " + user.getUsername());
    }

    // Returns a user's list of bookmarks
    // TODO double check
    public List<Recipe> getBookmarksFromFile(String username) {
        final Map<String, User> users = loadUsersFromFile();
        final User user = users.get(username);
        System.out.println("Loaded user: " + user.getUsername());
        System.out.println("Password: " + user.getPassword());
        System.out.println("Bookmarks: " + user.getBookmarks());
        System.out.println("RecentlyViewed: " + user.getRecentlyViewed());
        System.out.println(user);
        return user.getBookmarks();
    }

    // Add a recipe to a user's list of recentlyViewed
    public void addRecentlyViewedToFile(String username, Recipe recipe) {
        final Map<String, User> users = loadUsersFromFile();
        final User user = users.get(username);
        user.addRecentlyViewed(recipe);
        usersDatabase.put(user.getUsername(), user);
        saveUsersToFile();
        System.out.println("RecentlyViewed added successfully for: " + user.getUsername());
    }

    public List<Recipe> getRecentlyViewedFromFile(String username) {
        final Map<String, User> users = loadUsersFromFile();
        final User user = users.get(username);
        System.out.println("Loaded user: " + user.getUsername());
        System.out.println("Password: " + user.getPassword());
        System.out.println("Bookmarks: " + user.getBookmarks());
        System.out.println("RecentlyViewed: " + user.getRecentlyViewed());
        System.out.println(user);
        return user.getRecentlyViewed();
    }

    public void clearRecentlyViewed(String username) {
        final Map<String, User> users = loadUsersFromFile();
        final User user = users.get(username);
        user.clearRecentlyViewed();
        usersDatabase.put(user.getUsername(), user);
        saveUsersToFile();
        System.out.println("RecentlyViewed cleared successfully");
    }
}
