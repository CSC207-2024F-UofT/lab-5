package use_case.watchlist;

import entity.Movie;
import entity.User;

/**
 * The Watchlists Interactor.
 */
public class WatchlistInteractor implements WatchlistInputBoundary {
    private final WatchlistUserDataAccessInterface userDataAccessObject;
    private final WatchlistOutputBoundary userPresenter;

    public WatchlistInteractor(WatchlistUserDataAccessInterface watchlistDataAccessInterface,
                               WatchlistOutputBoundary watchlistOutputBoundary) {
        this.userDataAccessObject = watchlistDataAccessInterface;
        this.userPresenter = watchlistOutputBoundary;
    }

    @Override
    public void switchToHomeView() {
        userPresenter.switchToHomeView();
    }

    /**
     * Executes the switch to watchlist view use case.
     *
     * @param username name of the user that is currently logged in
     */
    @Override
    public void switchToWatchlistsView(String username) {
        final User currentUser = this.userDataAccessObject.get(username);
        userPresenter.switchToWatchlistsView(currentUser);
    }

    @Override
    public void switchToMovieSearchView(String username) {
        userPresenter.switchToMovieSearchView(username);
    }

    /**
     * Executes the "switch to MovieView" Use Case.
     * @param user logged in user
     * @param movie movie
     * @param ind index of the movie in the watchlist
     */
    @Override
    public void switchToMovieView(String user, Movie movie, int ind) {
        final User currentUser = this.userDataAccessObject.get(user);
        final boolean watched = currentUser.watchedBefore(movie);
        userPresenter.switchToMovieView(currentUser, movie, watched);
    }
}
