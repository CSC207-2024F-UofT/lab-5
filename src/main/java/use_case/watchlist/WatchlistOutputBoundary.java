package use_case.watchlist;

import entity.User;

/**
 * The output boundary for the Signup Use Case.
 */
public interface WatchlistOutputBoundary {

    /**
     * Switches to the Logged In View.
     */
    void switchToHomeView();

    /**
     * Switches to the Watchlist View.
     * @param currentUser user that is currently logged in
     */
    void switchToWatchlistsView(User currentUser);

    /**
     * Switches to the PWL View.
     * @param currentUser user that is currently logged in
     */
    void switchToMovieSearchView(String currentUser);
}
