package use_case.add_to_watchlist;

import entity.User;
import entity.Watchlist;

public interface AddToWatchlistInputBoundary {

    /**
     * Executes the signup use case.
     * @param user user
     * @param watchlist watchlist
     * @param movieTitle movie name
     */
    void execute(User user, Watchlist watchlist, String movieTitle);
}
