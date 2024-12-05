package use_case.create_watchlist;

import entity.User;
import entity.UserWatchlist;

/**
 * The create watchlist interface.
 */
public interface CreateWatchlistDataAccessInterface {

    /**
     * Saves the watchlist to user.
     * @param user user
     * @param watchlist to save
     * @return success
     */
    boolean saveWatchlist(User user, UserWatchlist watchlist);
}
