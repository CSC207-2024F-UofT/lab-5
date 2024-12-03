package use_case.create_watchlist;

import entity.User;

public interface CreateWatchlistInputBoundary {
    /**
     * Executes the signup use case.
     * @param user user
     * @param watchlistName the input data
     */
    void execute(User user, String watchlistName);
}
