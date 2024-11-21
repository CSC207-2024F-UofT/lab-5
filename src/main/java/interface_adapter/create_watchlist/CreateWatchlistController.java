package interface_adapter.create_watchlist;

import entity.Watchlist;
import use_case.create_watchlist.CreateWatchlistInputBoundary;
import use_case.create_watchlist.CreateWatchlistInputData;

/**
 * The controller for the Create Watchlist Use Case.
 */
public class CreateWatchlistController {
    private final CreateWatchlistInputBoundary useCaseInteractor;

    public CreateWatchlistController(CreateWatchlistInputBoundary useCaseInteractor) {
        this.useCaseInteractor = useCaseInteractor;
    }

    /**
     * Executes the Create Watchlist Use Case.
     * @param username username of current user
     * @param watchlist watchlist
     */
    public void execute(String username, Watchlist watchlist) {
        final CreateWatchlistInputData inputData = new CreateWatchlistInputData(username, watchlist);
        useCaseInteractor.execute(inputData);
    }
}
