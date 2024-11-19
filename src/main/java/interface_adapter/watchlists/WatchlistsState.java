package interface_adapter.watchlists;

import entity.User;

/**
 * The state for the Watchlists View Model.
 */
public class WatchlistsState {
    private User currentUser;

    public WatchlistsState() {

    }

    public User getCurrentUser() {
        return this.currentUser;
    }

    public void setCurrentUser(User user) {
        this.currentUser = user;
    }

    @Override
    public String toString() {
        return "WatchlistsState{"
                + "currentUser='" + currentUser
                + '}';
    }
}
