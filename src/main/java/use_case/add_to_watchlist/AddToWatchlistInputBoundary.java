package use_case.add_to_watchlist;

import entity.User;
import entity.Watchlist;

public interface AddToWatchlistInputBoundary {

    /**
     * Executes the signup use case.
     * @param user user
     * @param watchlistName watchlist
     * @param movieTitle movie name
     */
    void execute(User user, String watchlistName, String movieTitle);
}
