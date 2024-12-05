package entity;

/**
 * A factory for creating common watchlists.
 */
public class CommonWatchlistFactory {
    /**
     * The create method to create a commonWatchlist.
     * @return Watchlist object.
     */
    public Watchlist create() {
        return new CommonWatchlist();
    }
}
