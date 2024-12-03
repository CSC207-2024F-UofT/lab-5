package data_access;

import entity.Ingredient;
import entity.Recipe;
import entity.User;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;
import use_case.search_recipe_list_by_ingredient.SearchRecipeListByIngredientDataAccessInterface;
import use_case.search_recipe_list_by_name.SearchRecipeListByNameDataAccessInterface;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class UserDAOImpl implements UserDAO, SearchRecipeListByIngredientDataAccessInterface,
        SearchRecipeListByNameDataAccessInterface {
    private static final String FILE_PATH = "users.json";
    private Map<String, User> usersDatabase;

    public UserDAOImpl() {
        if (usersDatabase == null) {
            usersDatabase = new HashMap<>();
        }
        else {
            usersDatabase = loadUsersFromFile();
        }
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
        usersDatabase = loadUsersFromFile();
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
                        Ingredient ingredient = new Ingredient(
                                ingredientJson.getString("name"),
                                ingredientJson.getDouble("amount"),
                                ingredientJson.getString("unit"));
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
                JSONArray recentlyViewedArray = userObject.getJSONArray("recentlyViewed");
                List<Recipe> recipeRecentlyViewed = new ArrayList<>();
                for (int m = 0; m < recentlyViewedArray.length(); m++) {
                    JSONObject recipeJson = recentlyViewedArray.getJSONObject(m);
                    JSONArray ingredientsJson = recipeJson.getJSONArray("ingredients");
                    List<Ingredient> ingredients = new ArrayList<>();
                    for (int j = 0; j < ingredientsJson.length(); j++) {
                        JSONObject ingredientJson = ingredientsJson.getJSONObject(j);
                        System.out.println(ingredientJson.keys());
                        Ingredient ingredient = new Ingredient(
                                ingredientJson.getString("name"),
                                ingredientJson.getDouble("amount"),
                                ingredientJson.getString("unit"));
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

                // reconstructing folders as a map of folderNames and lists of recipes
                JSONObject foldersJson = new JSONObject();
                if (userObject.has("folders")) {
                    foldersJson = userObject.getJSONObject("folders");
                }
                else {
                    foldersJson.put("folders", new JSONArray());
                }
                Map<String, List<Recipe>> foldersMap = new HashMap<>();
                for (String folderName : foldersJson.keySet()) {
                    JSONArray recipesArray = foldersJson.getJSONArray(folderName);
                    List<Recipe> recipes = new ArrayList<>();

                    for (int t = 0; t < recipesArray.length(); t++) {
                        JSONObject recipeJson = recipesArray.getJSONObject(t);
                        JSONArray ingredientsJson = recipeJson.getJSONArray("ingredients");
                        List<Ingredient> ingredients = new ArrayList<>();

                        for (int s = 0; s < ingredientsJson.length(); s++) {
                            JSONObject ingredientJson = ingredientsJson.getJSONObject(s);
                            Ingredient ingredient = new Ingredient(
                                    ingredientJson.getString("name"),
                                    ingredientJson.getDouble("amount"),
                                    ingredientJson.getString("unit"));
                            ingredients.add(ingredient);
                        }

                        Recipe recipe = new Recipe(
                                recipeJson.getString("name"),
                                recipeJson.getString("url"),
                                ingredients,
                                recipeJson.getString("image"));
                        recipes.add(recipe);
                    }
                    System.out.println(folderName);
                    System.out.println(recipes);
                    foldersMap.put(folderName, recipes);
                }

                // putting the new user object in the list of users to be returned
                users.put(username, new User(username, password, recipeBookmarks, recipeRecentlyViewed, foldersMap));
                System.out.println("Loaded user: " + username); // Debugging output
            }
        } catch (IOException e) {
            e.printStackTrace();
            saveUsersToFile();
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
            userObject.put("folders", user.getFolders());
            usersArray.put(userObject);
        }
        try (FileWriter writer = new FileWriter(FILE_PATH)) {
            writer.write(usersArray.toString(4)); // Indent for readability
        } catch (IOException e) {
            e.printStackTrace();
            saveUsersToFile();
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

    public void addFolderToFile(String username, String folder) {
        final Map<String, User> users = loadUsersFromFile();
        final User user = users.get(username);
        user.addFolder(folder);
        usersDatabase.put(user.getUsername(), user);
        saveUsersToFile();
        System.out.println("Folder added successfully for: " + user.getUsername());
        // TODO double check this method
    }

    public void addRecipeToFolderInFile(String username, String folder, Recipe recipe) {
        final Map<String, User> users = loadUsersFromFile();
        final User user = users.get(username);
        user.addRecipeToFolder(folder, recipe);
        usersDatabase.put(user.getUsername(), user);
        saveUsersToFile();
        System.out.println("Recipe added successfully for: " + user.getFolder(folder));
    }

    public List<Recipe> getFolderFromFile(String username, String folder) {
        final Map<String, User> users = loadUsersFromFile();
        final User user = users.get(username);
        System.out.println("Loaded folder: " + user.getFolder(folder));
        return user.getFolder(folder);
    }

    @Override
    public List<Recipe> searchRecipeListByIngredient(List<String> ingredients, User user, String folder) {
        final Map<String, User> users = loadUsersFromFile();
        final User userFromFile = users.get(user.getUsername());
        List<Recipe> recipeList = new ArrayList<>();
        final List<Recipe> results = new ArrayList<>();
        if ("bookmarks".equals(folder)) {
            recipeList.addAll(userFromFile.getBookmarks());
        }
        else if ("recentlyViewed".equals(folder)) {
            recipeList.addAll(userFromFile.getRecentlyViewed());
        }
        else {
            recipeList.addAll(userFromFile.getFolder(folder));
        }
        for (Recipe recipe : recipeList) {
            boolean match = false;
            final List<Ingredient> recipeIngredients = recipe.getIngredients();
            final List<String> recipeIngredientsString = new ArrayList<>();
            for (Ingredient ingredient : recipeIngredients) {
                final String[] words = ingredient.getName().split(" ");
                recipeIngredientsString.addAll(Arrays.asList(words));
            }
            for (String recipeIngredient : recipeIngredientsString) {
                for (String enteredIngredient : ingredients) {
                    if (recipeIngredient.equalsIgnoreCase(enteredIngredient)) {
                        match = true;
                    }
                }

            }
            if (match) {
                results.add(recipe);
            }
        }
        return results;
    }

    @Override
    public List<Recipe> searchRecipeListByName(String recipeName, User user, String folder) {
        final Map<String, User> users = loadUsersFromFile();
        final User userFromFile = users.get(user.getUsername());
        final List<Recipe> results = new ArrayList<>();

        List<Recipe> recipeList = new ArrayList<>();
        if ("bookmarks".equals(folder)) {
            recipeList.addAll(userFromFile.getBookmarks());
        }
        else if ("recentlyViewed".equals(folder)) {
            recipeList.addAll(userFromFile.getRecentlyViewed());
        }
        else {
            recipeList.addAll(userFromFile.getFolder(folder));
        }

        for (Recipe recipe : recipeList) {
            final String[] words = recipe.getName().split(" ");
            for (String word : words) {
                if (word.toLowerCase().contains(recipeName.trim().toLowerCase())) {
                    results.add(recipe);
                }
            }
        }
        return results;
    }
}
