package data_access;

import entity.User;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
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
                users.put(username, new User(username, password));
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
            usersArray.put(userObject);
        }
        try (FileWriter writer = new FileWriter(FILE_PATH)) {
            writer.write(usersArray.toString(4)); // Indent for readability
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
