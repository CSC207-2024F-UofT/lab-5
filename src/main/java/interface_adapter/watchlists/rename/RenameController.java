package interface_adapter.watchlists.rename;

import entity.User;
import use_case.home.HomeInputBoundary;
import use_case.signup.SignupInputData;
import use_case.watchlists.rename.RenameInputBoundary;
import use_case.watchlists.rename.RenameInputData;

/**
 * Controller for the Change Password Use Case.
 */
public class RenameController {
    private final RenameInputBoundary renameInteractor;

    public RenameController(RenameInputBoundary renameInteractor) {
        this.renameInteractor = renameInteractor;
    }

    /**
     * Executes the "rename watchlists" Use Case.
     * @param currentUser user that is currently logged in
     * @param ind index of the watchlist in the list
     * @param newListName new name for the wacthlist
     */
    public void execute(User currentUser, int ind, String newListName) {
        final RenameInputData ranameInputData = new RenameInputData(currentUser, ind, newListName);

        renameInteractor.execute(ranameInputData);
    }

}
