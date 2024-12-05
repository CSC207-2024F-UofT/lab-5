package use_case.add_to_watchlist;

import entity.Movie;
import entity.User;
import entity.Watchlist;

/**
 * The input boundary for our add to watchlist use case.
 */
public interface AddToWatchlistInputBoundary {

    /**
     * Executes the add to watchlist use case.
     * @param user user
     * @param watchlist watchlist
     * @param movie movie
     */
    void execute(User user, Watchlist watchlist, Movie movie);

    /**
     * Executes the add to watchlist use case.
     * @param user user
     * @param ind index of watchlist
     * @param movie movie
     */
    void execute(User user, int ind, Movie movie);
}
