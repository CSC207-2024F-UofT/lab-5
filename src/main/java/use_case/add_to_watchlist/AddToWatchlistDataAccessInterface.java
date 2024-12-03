package use_case.add_to_watchlist;

import entity.Movie;
import entity.User;
import entity.Watchlist;

/**
 * DAO for the Add To Watchlist Use Case.
 */
public interface AddToWatchlistDataAccessInterface {

    /**
     * Gets username.
     * @param watchlist watchlist
     * @param user user
     * @param movie movie to add
     */
    void saveToWatchlist(User user, Watchlist watchlist, Movie movie);
}