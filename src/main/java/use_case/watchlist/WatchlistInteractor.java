package use_case.watchlist;

import entity.User;
import entity.UserFactory;

/**
 * The Watchlists Interactor.
 */
public class WatchlistInteractor implements WatchlistInputBoundary {
    private final WatchlistUserDataAccessInterface userDataAccessObject;
    private final WatchlistOutputBoundary userPresenter;
    private final UserFactory userFactory;

    public WatchlistInteractor(WatchlistUserDataAccessInterface watchlistDataAccessInterface,
                               WatchlistOutputBoundary watchlistOutputBoundary,
                               UserFactory userFactory) {
        this.userDataAccessObject = watchlistDataAccessInterface;
        this.userPresenter = watchlistOutputBoundary;
        this.userFactory = userFactory;
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
}
