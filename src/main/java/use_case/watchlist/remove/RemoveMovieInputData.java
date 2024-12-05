package use_case.watchlist.remove;

import entity.User;

/**
 * The Input Data for the Delete Use Case.
 */
public class RemoveMovieInputData {

    private final String currentUser;
    private final int watchlistIndex;
    private final int index;

    public RemoveMovieInputData(String currentUser, int watchlistIndex, int ind) {
        this.currentUser = currentUser;
        this.index = ind;
        this.watchlistIndex = watchlistIndex;
    }

    public String getCurrentUser() {
        return currentUser;
    }

    public int getIndex() {
        return index;
    }

    public int getWatchlistIndex() {
        return watchlistIndex;
    }
}
