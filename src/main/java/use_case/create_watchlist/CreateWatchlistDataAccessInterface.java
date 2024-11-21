package use_case.create_watchlist;

import entity.Watchlist;

public interface CreateWatchlistDataAccessInterface {

    String getCurrentUsername();

    void saveToWatchlists(String username, Watchlist watchlist);
}
