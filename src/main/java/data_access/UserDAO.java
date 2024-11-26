package data_access;

import entity.User;

public interface UserDAO {
    boolean addUser(User user); // Returns true if the user is added successfully
    User findUserByUsername(String username); // Finds a user by their username
    boolean validateUser(String username, String password); // Validates username and password
}
