package data_access;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import entity.CommonUserFactory;
import entity.Recipe;
import entity.User;
import entity.UserFactory;
import use_case.change_password.ChangePasswordUserDataAccessInterface;
import use_case.create.CreateUserDataAccessInterface;
import use_case.favorite_receipe.FavoriteRecipeDataAccessInterface;
import use_case.like_and_dislike_a_recipe.UserLikeAndDislikeDataAccessInterface;
import use_case.login.LoginUserDataAccessInterface;
import use_case.logout.LogoutUserDataAccessInterface;
import use_case.shopping_list.ShoppingListDataAccessInterface;
import use_case.signup.SignupUserDataAccessInterface;

/**
 * In-memory implementation of the DAO for storing user data. This implementation does
 * NOT persist data between runs of the program.
 */
public class InMemoryUserDataAccessObject implements SignupUserDataAccessInterface,
        LoginUserDataAccessInterface,
        ChangePasswordUserDataAccessInterface,
        UserLikeAndDislikeDataAccessInterface,
        FavoriteRecipeDataAccessInterface,
        ShoppingListDataAccessInterface,
        LogoutUserDataAccessInterface,
        CreateUserDataAccessInterface {

    private static final String FILE_IO_API_URL = "https://file.io";
    private static final String API_KEY = "35F52QF.ZQV4A4E-ASHMAQD-QSPTZ93-NHYCJT6";
    private static final int STATUS_CODE_OK = 200;
    private static final String FILE_PATH = "all_users.json";
    private static String userFileKey = "";
    private static final String AUTHORIZATION = "Authorization";
    private static final String BEARER = "Bearer ";
    private static final String NODES = "nodes";
    private static final String NAME = "name";
    private static final String KEY = "key";
    private Map<String, User> users = new HashMap<>();
    private String currentUsername;
    private String username;
    private String[] favoriteRecipes;

    @Override
    public String findFileOnFileIo(String fileName) {
        try {
            // Initialize recipeFileKey to null for a single return statement at the end
            userFileKey = "";

            // Properly format the search URL with the provided file name
            final String searchUrl = FILE_IO_API_URL + "/?search=" + URLEncoder.encode(
                    fileName, StandardCharsets.UTF_8);
            final HttpClient client = HttpClient.newHttpClient();
            final HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(searchUrl))
                    .header("accept", "application/json")
                    .header(AUTHORIZATION, BEARER + API_KEY)
                    .GET()
                    .build();

            final HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() == STATUS_CODE_OK) {
                // Parse the response
                final JsonObject responseObject = JsonParser.parseString(response.body()).getAsJsonObject();
                if (responseObject.has(NODES) && responseObject.get(NODES).isJsonArray()) {
                    final JsonArray nodesArray = responseObject.getAsJsonArray(NODES);
                    processNodes(nodesArray, fileName);
                }
                else {
                    System.out.println("No 'nodes' array found in the response. Response: " + response.body());
                }
            }
            else {
                System.out.println("Failed to get user file list from File.io. Status code: " + response.statusCode());
            }
        }
        catch (IOException | InterruptedException ex) {
            System.err.println("Error while searching for file on File.io: " + ex.getMessage());
            Thread.currentThread().interrupt();
        }

        // Single return statement
        return userFileKey;
    }

    /**
     * Processes a JSON array of nodes to locate a specific file object by its name and retrieve its associated key.
     * This method iterates through the provided JSON array, checks each element to see if it is a JSON object,
     * and looks for a node matching the specified file name. If a matching node is found and contains a key,
     * the key's value is assigned to the `userFileKey` field. Messages are logged to indicate the result
     * of the search operation, whether the file and key were found or if the file exists without a key.
     *
     * @param nodesArray the JSON array containing node objects to be processed
     * @param fileName   the name of the file to search for within the node objects
     */
    private void processNodes(JsonArray nodesArray, String fileName) {
        boolean fileFound = false;

        for (JsonElement nodeElement : nodesArray) {
            if (nodeElement.isJsonObject()) {
                final JsonObject nodeObject = nodeElement.getAsJsonObject();

                if (nodeObject.has(NAME) && nodeObject.get(NAME).getAsString().equals(fileName)) {
                    if (nodeObject.has(KEY)) {
                        userFileKey = nodeObject.get(KEY).getAsString();
                        System.out.println("File '" + fileName + "' found on File.io with key: " + userFileKey);
                    }
                    else {
                        System.out.println("File object found, but no key present for file: " + fileName);
                    }
                    fileFound = true;
                    break;
                }
            }
        }

        if (!fileFound) {
            System.out.println("File '" + fileName + "' not found in the nodes.");
        }
    }

    /**
     * Deletes a file from File.io using its unique file key.
     * This method constructs a DELETE request to the File.io API with the provided file key.
     * If the file key is empty, the method logs an error and exits without making a request.
     * On a successful deletion, it logs a success message. If the deletion fails, it logs
     * the HTTP status code and response body. Any exceptions during the process are caught
     * and logged, with the thread being interrupted in case of an `InterruptedException`.
     * Preconditions:
     * - `userFileKey` must be set to a valid file key.
     *
     * @throws IOException          if an I/O error occurs when sending or receiving the request
     * @throws InterruptedException if the operation is interrupted during execution
     */
    public void deleteFileFromFileIo() {
        if (userFileKey.isEmpty()) {
            System.err.println("User File key is empty. Cannot delete file.");
            return;
        }

        System.out.println("Deleting User file from File.io with User File key: " + userFileKey);
        try {
            String deleteUrl = FILE_IO_API_URL + "/" + URLEncoder.encode(userFileKey, StandardCharsets.UTF_8);
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(deleteUrl))
                    .DELETE()
                    .header(AUTHORIZATION, BEARER + API_KEY)
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() == STATUS_CODE_OK) {
                System.out.println("User File deleted successfully: " + response.body());
            } else {
                System.err.println("Failed to delete User file. Status code: " + response.statusCode());
                System.err.println("Response body: " + response.body());
            }
        } catch (IOException | InterruptedException e) {
            System.err.println("Error during User file deletion: " + e.getMessage());
            Thread.currentThread().interrupt();
        }
    }

    /**
     * Writes the list of users in JSON format to a file.
     */
    public void writeUsersToFile(Map<String, User> allUsers) {
        System.out.println("Writing all users to JSON file.");
        final File file = new File(FILE_PATH);

        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String jsonContent = gson.toJson(allUsers);

        try (FileWriter writer = new FileWriter(FILE_PATH)) {
            writer.write(jsonContent);
            System.out.println("All user data written to file successfully.");
        } catch (IOException e) {
            System.err.println("Error while writing to User file: " + e.getMessage());
        }
    }

    public void uploadFileToFileIo() {
        System.out.println("Uploading file to File.io with Bearer Auth.");
        try {
            final HttpClient client = HttpClient.newHttpClient();
            String bearerToken = API_KEY; // Replace this with your actual token

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(FILE_IO_API_URL))
                    .header(AUTHORIZATION, BEARER + bearerToken)
                    .header("Content-Type", "multipart/form-data; boundary=----WebKitFormBoundary")
                    .POST(ofFileUpload(Path.of(FILE_PATH)))
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() == STATUS_CODE_OK) {
                System.out.println("User File uploaded successfully: " + response.body());
                // Parse the response to extract the "key" value and set FILE_KEY
                JsonObject jsonResponse = JsonParser.parseString(response.body()).getAsJsonObject();
                userFileKey = jsonResponse.get(KEY).getAsString();
                System.out.println("User File key set to: " + userFileKey);
            } else {
                System.err.println("Failed to upload User file. Status code: " + response.statusCode());
                System.err.println("Response body: " + response.body());
            }
        } catch (IOException | InterruptedException e) {
            System.err.println("Error during User file upload: " + e.getMessage());
            Thread.currentThread().interrupt();
        }
    }

    /**
     * Constructs a multipart request body for file upload.
     *
     * @param path the path to the file to upload
     * @return a BodyPublisher for the multipart file upload
     * @throws IOException if there is an error reading the file
     */
    public static HttpRequest.BodyPublisher ofFileUpload(Path path) throws IOException {
        var boundary = "----WebKitFormBoundary";
        var fileBytes = Files.readAllBytes(path);
        var byteArrays = new ArrayList<byte[]>();

        byteArrays.add(("--" + boundary + "\r\nContent-Disposition: form-data; name=\"file\"; filename=\""
                + path.getFileName() + "\"\r\nContent-Type: application/json\r\n\r\n").getBytes(StandardCharsets.UTF_8));
        byteArrays.add(fileBytes);
        byteArrays.add(("\r\n--" + boundary + "--\r\n").getBytes(StandardCharsets.UTF_8));

        return HttpRequest.BodyPublishers.ofByteArrays(byteArrays);
    }

    @Override
    public void loadUsersFromCloud() {
        if (userFileKey.isEmpty()) {
            System.err.println("User File key is empty. Cannot download user file.");
        }

        System.out.println("Downloading User file from File.io with key: " + userFileKey);
        try {
            String downloadUrl = FILE_IO_API_URL + "/" + URLEncoder.encode(userFileKey, StandardCharsets.UTF_8);
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(downloadUrl))
                    .GET()
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() == STATUS_CODE_OK) {
                System.out.println("User File downloaded successfully.");

                // Log the entire JSON content received
                String jsonContent = response.body();

                // Parse the downloaded JSON content
                users = parseDownloadedUsers(jsonContent);

                // Write parsed recipes back to the JSON file
                writeUsersToFile(users);

                // Upload the updated JSON file immediately
                uploadFileToFileIo();

            } else {
                System.err.println("Failed to download User file. Status code: " + response.statusCode());
            }
        } catch (IOException | InterruptedException e) {
            System.err.println("Error during user file download: " + e.getMessage());
            Thread.currentThread().interrupt();
        }
    }

    /**
     * Parses the downloaded JSON content into a Map of users.
     *
     * @param jsonContent the JSON string containing the user data
     * @return a Map of users parsed from the JSON content
     */
    private Map<String, User> parseDownloadedUsers(String jsonContent) {
        System.out.println("Parsing downloaded users from JSON content...");

        Gson gson = new Gson();
        Map<String, User> parsedUsers = new HashMap<>();

        try {
            // Parse the JSON into a map of raw JSON objects
            Map<String, JsonObject> rawUsers = gson.fromJson(jsonContent, new TypeToken<Map<String, JsonObject>>() {}.getType());

            if (rawUsers != null && !rawUsers.isEmpty()) {
                for (Map.Entry<String, JsonObject> entry : rawUsers.entrySet()) {
                    String username = entry.getKey();
                    JsonObject userObject = entry.getValue();

                    // Extract necessary fields from the JSON object
                    String password = userObject.get("password").getAsString();
                    JsonArray likedRecipesArray = userObject.getAsJsonArray("likedRecipes");
                    JsonArray dislikedRecipesArray = userObject.getAsJsonArray("dislikedRecipes");
                    JsonArray favoriteRecipesArray = userObject.getAsJsonArray("favoriteRecipes");

                    // Use UserFactory to create the User object
                    UserFactory userFactory = new CommonUserFactory();
                    User user = userFactory.create(username, password);

                    // Populate additional fields (e.g., liked/disliked recipes, favorite recipes)
                    for (JsonElement recipe : likedRecipesArray) {
                        user.addLikedRecipe(recipe.getAsString());
                    }
                    for (JsonElement recipe : dislikedRecipesArray) {
                        user.addDislikedRecipe(recipe.getAsString());
                    }
                    String[] favoriteRecipes = new String[favoriteRecipesArray.size()];
                    for (int i = 0; i < favoriteRecipesArray.size(); i++) {
                        favoriteRecipes[i] = favoriteRecipesArray.get(i).isJsonNull() ? null : favoriteRecipesArray.get(i).getAsString();
                    }
                    user.setFavoriteRecipes(favoriteRecipes);

                    // Add the constructed user to the map
                    parsedUsers.put(username, user);
                }
                System.out.println("Successfully parsed users:");
                parsedUsers.forEach((key, value) -> System.out.println("User: " + key + ", Details: " + value));
            } else {
                System.out.println("No users found in the JSON content.");
            }
        } catch (JsonSyntaxException | IllegalStateException e) {
            System.err.println("Error parsing JSON content: " + e.getMessage());
        }

        return parsedUsers;
    }



    @Override
    public boolean existsByName(String identifier) {
        return users.containsKey(identifier);
    }

    @Override
    public void save(User user) {
        users.put(user.getName(), user);
        if (!userFileKey.isEmpty()) {
            deleteFileFromFileIo();
        }
        writeUsersToFile(users);
        uploadFileToFileIo();
    }

    @Override
    public User get(String username) {
        return users.get(username);
    }

    @Override
    public Recipe getOneRecipe(String dishName) {
        return null;
    }

    @Override
    public void changePassword(User user) {
        // Replace the old entry with the new password
        users.put(user.getName(), user);
        deleteFileFromFileIo();
        writeUsersToFile(users);
        uploadFileToFileIo();
    }

    @Override
    public void setCurrentUsername(String name) {
        this.currentUsername = name;
    }

    @Override
    public String getCurrentUsername() {
        return this.currentUsername;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public String[] getFavoriteRecipes() {
        return favoriteRecipes;
    }

    @Override
    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public void setFavoriteRecipes(String[] favoriteRecipes) {
        this.favoriteRecipes = favoriteRecipes;
    }

    @Override
    public void updateUserFavoriteRecipes(User user) {
        users.put(user.getName(), user);
        deleteFileFromFileIo();
        writeUsersToFile(users);
        uploadFileToFileIo();
    }

    @Override
    public boolean hasUserLikedRecipe(String recipeName) {
        final User currentUser = get(getCurrentUsername());
        return currentUser.hasUserLikedRecipe(recipeName);
    }

    @Override
    public void addLikedRecipe(String recipeName) {
        final User currentUser = get(getCurrentUsername());
        currentUser.addLikedRecipe(recipeName);
    }

    @Override
    public boolean hasUserDislikedRecipe(String recipeName) {
        final User currentUser = get(getCurrentUsername());
        return currentUser.hasUserDislikedRecipe(recipeName);
    }

    @Override
    public void addDislikedRecipe(String recipeName) {
        final User currentUser = get(getCurrentUsername());
        currentUser.addDislikedRecipe(recipeName);
    }

    @Override
    public void updateUserLikedRecipe(String recipeName) {
        final User currentUser = get(getCurrentUsername());
        // Replace the old entry with the new password
        users.put(currentUser.getName(), currentUser);
        deleteFileFromFileIo();
        writeUsersToFile(users);
        uploadFileToFileIo();
    }

    @Override
    public void updateUserDislikedRecipe(String recipeName) {
        final User currentUser = get(getCurrentUsername());
        // Replace the old entry with the new password
        users.put(currentUser.getName(), currentUser);
        deleteFileFromFileIo();
        writeUsersToFile(users);
        uploadFileToFileIo();
    }

    @Override
    public void addCreatedRecipe(Recipe recipe) {
        final User currentUser = get(getCurrentUsername());
        currentUser.addCreatedRecipe(recipe);
    }
}
