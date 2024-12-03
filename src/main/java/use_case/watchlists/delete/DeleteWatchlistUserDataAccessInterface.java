package use_case.watchlists.delete;

import entity.User;

/**
 * DAO for the Signup Use Case.
 */
public interface DeleteWatchlistUserDataAccessInterface {

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

    boolean deleteWatchlist(User user, int ind);

}
