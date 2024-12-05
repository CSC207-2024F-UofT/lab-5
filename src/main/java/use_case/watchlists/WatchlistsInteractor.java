package use_case.watchlists;

import entity.User;
import entity.UserFactory;

/**
 * The Watchlists Interactor.
 */
public class WatchlistsInteractor implements WatchlistsInputBoundary {
    private final WatchlistsUserDataAccessInterface userDataAccessObject;
    private final WatchlistsOutputBoundary userPresenter;
    private final UserFactory userFactory;

    public WatchlistsInteractor(WatchlistsUserDataAccessInterface watchlistsDataAccessInterface,
                                WatchlistsOutputBoundary watchlistsOutputBoundary,
                                UserFactory userFactory) {
        this.userDataAccessObject = watchlistsDataAccessInterface;
        this.userPresenter = watchlistsOutputBoundary;
        this.userFactory = userFactory;
    }

    @Override
    public void switchToHomeView() {
        userPresenter.switchToHomeView();
    }

    /**
     * Executes the switch to watchlist view use case.
     *
     * @param currentUser user that is currently logged in
     * @param ind         index that corresponds to the watchlist to switch to
     */
    @Override
    public void switchToWatchlistView(User currentUser, int ind) {
        userPresenter.switchToWatchlistView(currentUser, ind);
    }

    /**
     * Executes the switch to PWL view use case.
     *
     * @param currentUser user that is currently logged in
     */
    @Override
    public void switchToPWL(User currentUser) {
        userPresenter.switchToPWLView(currentUser);
    }
}
