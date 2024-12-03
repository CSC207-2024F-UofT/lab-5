package interface_adapter.watchlists;

import entity.User;
import use_case.signup.SignupInputData;
import use_case.watchlists.WatchlistsInputBoundary;

/**
 * Controller for the Signup Use Case.
 */
public class WatchlistsController {

    private final WatchlistsInputBoundary watchlistsInteractor;

    public WatchlistsController(WatchlistsInputBoundary watchlistsInteractor) {
        this.watchlistsInteractor = watchlistsInteractor;
    }

    /**
     * Executes the "switch to HomeView" Use Case.
     */
    public void switchToHomeView() {
        watchlistsInteractor.switchToHomeView();
    }

    /**
     * Executes the "switch to WatchlistView" Use Case.
     * @param currentUser user that is currently logged in
     * @param ind index that corresponds to the watchlist to switch to
     */
    public void goToWatchlist(User currentUser, int ind) {
        watchlistsInteractor.switchToWatchlistView(currentUser, ind);
    }

    /**
     * Executes the "switch to PWLView" Use Case.
     * @param currentUser user that is currently logged in
     */
    public void goToPWL(User currentUser) {
        watchlistsInteractor.switchToPWL(currentUser);
    }
}
