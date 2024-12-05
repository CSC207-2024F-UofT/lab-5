package use_case.watchlist.remove;

import entity.User;

/**
 * The output boundary for the Rename Use Case.
 */
public interface RemoveMovieOutputBoundary {

    /**
     * Updates the watchlists view after the watchlist is renamed.
     * @param currentUser logged in user
     */
    void execute(User currentUser);

    /**
     * Updates the watchlists view after the watchlist is renamed.
     * @param currentUser logged in user
     * @param watchlistIndex current watchlist
     */
    void execute(User currentUser, int watchlistIndex);
}
