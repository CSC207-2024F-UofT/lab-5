package interface_adapter.watchlist;

import entity.Movie;
import use_case.watchlist.WatchlistInputBoundary;

/**
 * Controller for the Signup Use Case.
 */
public class WatchlistController {

    private final WatchlistInputBoundary watchlistInteractor;

    public WatchlistController(WatchlistInputBoundary watchlistInteractor) {
        this.watchlistInteractor = watchlistInteractor;
    }

    /**
     * Executes the "switch to HomeView" Use Case.
     */
    public void switchToHomeView() {
        watchlistInteractor.switchToHomeView();
    }

    /**
     * Executes the "switch to WatchlistsView" Use Case.
     * @param currentUser user that is currently logged in
     */
    public void backToWatchlistsView(String currentUser) {

        watchlistInteractor.switchToWatchlistsView(currentUser);
    }

    /**
     * Executes the "switch to PWLView" Use Case.
     * @param currentUser user that is currently logged in
     */
    public void switchToMovieSearchView(String currentUser) {
        watchlistInteractor.switchToMovieSearchView(currentUser);
    }

    /**
     * Executes the "switch to MovieView" Use Case.
     * @param user logged in user
     * @param movie movie
     * @param ind index of the movie in the watchlist
     */
    public void switchToMovieView(String user, Movie movie, int ind) {
        watchlistInteractor.switchToMovieView(user, movie, ind);
    }
}
