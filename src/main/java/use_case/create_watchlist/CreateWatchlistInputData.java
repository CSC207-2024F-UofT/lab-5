package use_case.create_watchlist;

import entity.Watchlist;

/**
 * Input Data for creating watchlist.
 */
public class CreateWatchlistInputData {
    private final String username;
    private final Watchlist watchlist;

    public CreateWatchlistInputData(String username, Watchlist watchlist) {
        this.username = username;
        this.watchlist = watchlist;
    }

    public String getUsername() {
        return username;
    }

    public Watchlist getWatchlist() {
        return watchlist;
    }
}
