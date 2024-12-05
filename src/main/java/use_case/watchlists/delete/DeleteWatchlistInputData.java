package use_case.watchlists.delete;

import entity.User;

/**
 * The Input Data for the Delete Use Case.
 */
public class DeleteWatchlistInputData {

    private final User currentUser;
    private final int index;

    public DeleteWatchlistInputData(User currentUser, int ind) {
        this.currentUser = currentUser;
        this.index = ind;
    }

    public User getCurrentUser() {
        return currentUser;
    }

    public int getIndex() {
        return index;
    }
}
