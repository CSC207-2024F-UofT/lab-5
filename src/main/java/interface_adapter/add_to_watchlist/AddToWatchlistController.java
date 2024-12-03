package interface_adapter.add_to_watchlist;

import entity.User;
import entity.Watchlist;
import use_case.add_to_watchlist.AddToWatchlistInputBoundary;

/**
 * The controller for the Add To Watchlist Use Case.
 */
public class AddToWatchlistController {
    private final AddToWatchlistInputBoundary useCaseInteractor;

    public AddToWatchlistController(AddToWatchlistInputBoundary useCaseInteractor) {
        this.useCaseInteractor = useCaseInteractor;
    }

    /**
     * The controller for the Add To Watchlist Use Case.
     * @param user User adding to watchlist.
     * @param watchlistName watchlist to add to
     * @param movie The movie being added to watchlist.
     */
    public void execute(User user, String watchlistName, String movie) {
        useCaseInteractor.execute(user, watchlistName, movie);
    }
}