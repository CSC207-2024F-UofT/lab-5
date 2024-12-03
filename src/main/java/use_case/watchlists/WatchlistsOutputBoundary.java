package use_case.watchlists;

import entity.User;

/**
 * The output boundary for the Signup Use Case.
 */
public interface WatchlistsOutputBoundary {

    /**
     * Switches to the Logged In View.
     */
    void switchToHomeView();

    /**
     * Switches to the Watchlist View.
     * @param currentUser user that is currently logged in
     * @param ind         index that corresponds to the watchlist to switch to
     */
    void switchToWatchlistView(User currentUser, int ind);

    /**
     * Switches to the PWL View.
     * @param currentUser user that is currently logged in
     */
    void switchToPWLView(User currentUser);
}
