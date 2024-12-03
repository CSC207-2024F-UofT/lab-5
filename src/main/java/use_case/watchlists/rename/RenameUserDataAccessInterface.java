package use_case.watchlists.rename;

import entity.User;

/**
 * DAO for the Signup Use Case.
 */
public interface RenameUserDataAccessInterface {

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

    boolean renameWatchlist(User user, int ind, String newName);
}
