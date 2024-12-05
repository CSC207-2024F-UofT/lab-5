package use_case.watchlist;

import entity.Movie;

/**
 * Input Boundary for actions which are related to watchlists.
 */
public interface WatchlistInputBoundary {

    /**
     * Executes the switch to home view use case.
     */
    void switchToHomeView();

    /**
     * Executes the switch to watchlists view use case.
     * @param username name of the user that is currently logged in
     */
    void switchToWatchlistsView(String username);

    /**
     * Executes the switch to search view for add movie to watchlist use case.
     * @param username name of the user that is currently logged in
     */
    void switchToMovieSearchView(String username);

    /**
     * Executes the "switch to MovieView" Use Case.
     * @param user logged in user
     * @param movie movie
     * @param ind index of the movie in the watchlist
     */
    void switchToMovieView(String user, Movie movie, int ind);

}
