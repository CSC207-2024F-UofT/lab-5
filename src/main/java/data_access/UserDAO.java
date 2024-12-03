package data_access;

import entity.User;

public interface UserDAO {
    // Returns true if the user is added successfully
    boolean addUser(User user);

    // Finds a user by their username
    User findUserByUsername(String username);

    // Validates username and password
    boolean validateUser(String username, String password);
}
