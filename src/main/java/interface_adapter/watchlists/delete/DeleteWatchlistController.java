package interface_adapter.watchlists.delete;

import entity.User;
import use_case.watchlists.delete.DeleteWatchlistInputBoundary;
import use_case.watchlists.delete.DeleteWatchlistInputData;
import use_case.watchlists.rename.RenameInputBoundary;
import use_case.watchlists.rename.RenameInputData;

/**
 * Controller for the Change Password Use Case.
 */
public class DeleteWatchlistController {
    private final DeleteWatchlistInputBoundary deleteInteractor;

    public DeleteWatchlistController(DeleteWatchlistInputBoundary deleteInteractor) {
        this.deleteInteractor = deleteInteractor;
    }

    /**
     * Executes the "delete watchlists" Use Case.
     * @param currentUser user that is currently logged in
     * @param ind index of the watchlist in the list
     */
    public void execute(User currentUser, int ind) {
        final DeleteWatchlistInputData deleteInputData = new DeleteWatchlistInputData(currentUser, ind);

        deleteInteractor.execute(deleteInputData);
    }

}
