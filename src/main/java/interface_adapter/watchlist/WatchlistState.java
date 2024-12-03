package interface_adapter.watchlist;

import entity.User;
import entity.Watchlist;

/**
 * The state for the Watchlist View Model.
 */
public class WatchlistState {

    private String currentUser;
    private int watchlistIndex;
    private String watchlistName = "";
    private Watchlist watchlist;

    public String getCurrentUser() {
        return this.currentUser;
    }

    /**
     * Updates the state of the previously watched list that is displayed.
     * watchedlistIndex is set to -1.
     * @param user currently logged-in User
     */
    public void updateState(User user) {
        this.currentUser = user.getName();
        this.watchlistIndex = -1;
        this.watchlist = user.getPwl();
        this.watchlistName = "Previously watched list";
    }

    /**
     * Updates the state of the watchlist that is displayed.
     * @param user currently logged-in User
     * @param watchlistInd the index of this watchlist in the list
     */
    public void updateState(User user, int watchlistInd) {
        this.currentUser = user.getName();
        this.watchlistIndex = watchlistInd;
        this.watchlist = user.getWatchlists().get(watchlistIndex);
        this.watchlistName = this.watchlist.getListName();

    }

    public int getWatchlistIndex() {
        return watchlistIndex;
    }

    public String getWatchlistName() {
        return watchlistName;
    }

    public Watchlist getWatchlist() {
        return watchlist;
    }

    @Override
    public String toString() {
        return "WatchlistsState{"
                + "currentUser='" + currentUser
                + "watchlistIndex=" + watchlistIndex
                + "watchlistName=" + watchlistName
                + "watchlist=" + watchlist.toString()
                + '}';
    }
}
