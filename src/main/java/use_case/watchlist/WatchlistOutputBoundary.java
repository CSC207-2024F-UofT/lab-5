package use_case.watchlist;

import entity.Movie;
import entity.User;

/**
 * The output boundary for the Signup Use Case.
 */
public interface WatchlistOutputBoundary {

    /**
     * Switches to the Logged In View.
     */
    void switchToHomeView();

    /**
     * Switches to the Watchlist View.
     * @param currentUser user that is currently logged in
     */
    void switchToWatchlistsView(User currentUser);

    /**
     * Switches to the PWL View.
     * @param currentUser user that is currently logged in
     */
    void switchToMovieSearchView(String currentUser);

    /**
     * Executes the "switch to MovieView" Use Case.
     * @param aCurrentUser logged in user
     * @param aCurrentMovie current movie
     * @param watchedData if user watched a movie
     */
    void switchToMovieView(User aCurrentUser, Movie aCurrentMovie, boolean watchedData);
}
