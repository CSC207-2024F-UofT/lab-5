package use_case.add_to_watchlist;

import entity.Movie;
import entity.User;
import entity.UserWatchlist;
import entity.Watchlist;

/**
 * DAO for the Add To Watchlist Use Case.
 */
public interface AddToWatchlistDataAccessInterface {

    /**
     * Gets username.
     * @param watchlistName watchlist
     * @param user user
     * @param movie movie to add
     * @return success
     */
    boolean saveToWatchlist(User user, String watchlistName, Movie movie);

    /**
     * Gets username.
     * @param user user
     * @param movie movie to add
     * @return success
     */
    boolean saveToPwl(User user, Movie movie);
}