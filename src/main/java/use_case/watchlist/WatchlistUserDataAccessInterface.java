package use_case.watchlist;

import entity.User;

/**
 * DAO for the Signup Use Case.
 */
public interface WatchlistUserDataAccessInterface {

    /**
     * Checks if the given username exists.
     * @param username the username to look for
     * @return true if a user with the given username exists; false otherwise
     */
    boolean existsByName(String username);

    /**
     * Saves the user.
     * @param user the user to save
     */
    void save(User user);

    /**
     * Returns a User with this username.
     * @param username username of the user
     * @return User with this username
     */
    User get(String username);
}
