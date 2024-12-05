
package interface_adapter.add_to_watchlist;

import entity.Movie;
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
     * @param watchlist watchlist to add to
     * @param movie The movie being added to watchlist.
     */
    public void execute(User user, Watchlist watchlist, Movie movie) {
        useCaseInteractor.execute(user, watchlist, movie);
    }

    /**
     * The controller for the Add To Watchlist Use Case.
     * @param user User adding to watchlist.
     * @param ind index of watchlist to add to
     * @param movie The movie being added to watchlist.
     */
    public void execute(User user, int ind, Movie movie) {
        useCaseInteractor.execute(user, ind, movie);
    }
}