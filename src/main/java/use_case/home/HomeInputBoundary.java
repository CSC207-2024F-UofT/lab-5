package use_case.home;

import entity.User;

/**
 * The Change Password Use Case.
 */
public interface HomeInputBoundary {

    /**
     * Executes the Switch to Watchlists View Use Case.
     * @param username username of the currently logged-in user
     */
    void switchToWatchlistsView(String username);

    /**
     * Executes the Switch to Search Results View Use Case.
     * @param title username of the currently logged-in user
     */
    void switchToSearchResultsView(String title);

    /**
     * Return the user object associated to the given the username.
     * @param username the username of the user object.
     * @return
     */
    User getUser(String username);

}
