
package use_case.add_to_watchlist;

import entity.Movie;
import entity.User;

/**
 * DAO for the Add To Watchlist Use Case.
 */
public interface AddToWatchlistDataAccessInterface {

    /**
     * Gets username.
     * @param ind watchlist index
     * @param user user
     * @param movie movie to add
     * @return success
     */
    boolean saveToWatchlist(User user, int ind, Movie movie);

    /**
     * Gets username.
     * @param user user
     * @param movie movie to add
     * @return success
     */
    boolean saveToPwl(User user, Movie movie);
}