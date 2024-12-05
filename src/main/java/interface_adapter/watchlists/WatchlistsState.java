package interface_adapter.watchlists;

import entity.User;

/**
 * The state for the Watchlists View Model.
 */
public class WatchlistsState {
    private User currentUser;
    private String emptyListNameError = "Can't create untitled list";

    public User getCurrentUser() {
        return this.currentUser;
    }

    public void setCurrentUser(User user) {
        this.currentUser = user;
    }

    public String getEmptyListNameError() {
        return emptyListNameError;
    }

    public void setEmptyListNameError(String emptyListNameError) {
        this.emptyListNameError = emptyListNameError;
    }

    @Override
    public String toString() {
        return "WatchlistsState{"
                + "currentUser='" + currentUser.getName()
                + '}';
    }
}
