package interface_adapter.watchlist.remove;

import use_case.watchlist.remove.RemoveMovieInputBoundary;
import use_case.watchlist.remove.RemoveMovieInputData;

/**
 * Controller for the Change Password Use Case.
 */
public class RemoveMovieController {
    private final RemoveMovieInputBoundary deleteInteractor;

    public RemoveMovieController(RemoveMovieInputBoundary deleteInteractor) {
        this.deleteInteractor = deleteInteractor;
    }

    /**
     * Executes the "delete watchlists" Use Case.
     * @param currentUser user that is currently logged in
     * @param watchlistIndex index of the watchlist
     * @param ind index of the movie in the list
     */
    public void execute(String currentUser, int watchlistIndex, int ind) {
        final RemoveMovieInputData deleteInputData = new RemoveMovieInputData(currentUser, watchlistIndex, ind);

        deleteInteractor.execute(deleteInputData);
    }

}
