package interface_adapter.create_watchlist;

import entity.User;
import use_case.create_watchlist.CreateWatchlistInputBoundary;

/**
 * Controller for Create Watchlist Use Case.
 */
public class CreateWatchlistController {
    private CreateWatchlistInputBoundary useCaseInteractor;

    public CreateWatchlistController(CreateWatchlistInputBoundary useCaseInteractor) {
        this.useCaseInteractor = useCaseInteractor;
    }

    /**
     * ...
     * @param user user
     * @param watchlistName name
     */
    public void execute(User user, String watchlistName) {
        useCaseInteractor.execute(user, watchlistName);
    }
}
