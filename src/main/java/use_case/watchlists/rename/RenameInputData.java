package use_case.watchlists.rename;

import entity.User;

/**
 * The Input Data for the Rename Use Case.
 */
public class RenameInputData {

    private final User currentUser;
    private final int index;
    private final String newListName;

    public RenameInputData(User currentUser, int ind, String newListName) {
        this.currentUser = currentUser;
        this.index = ind;
        this.newListName = newListName;
    }

    public User getCurrentUser() {
        return currentUser;
    }

    public int getIndex() {
        return index;
    }

    public String getNewListName() {
        return newListName;
    }
}
