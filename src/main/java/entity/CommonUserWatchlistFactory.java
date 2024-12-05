package entity;

/**
 * The factory for creating watch lists for the common user.
 */
public class CommonUserWatchlistFactory {
    /**
     * Create a watchlist given this userID.
     * @param listName the list of movies to be added to the watchlist.
     * @return a user watch list object.
     */
    public UserWatchlist create(String listName) {
        return new CommonUserWatchlist(listName);
    }
}
