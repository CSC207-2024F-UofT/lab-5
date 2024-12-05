package interface_adapter.watchlist.remove;

import entity.User;
import interface_adapter.ViewManagerModel;
import interface_adapter.watchlist.WatchlistViewModel;
import use_case.watchlist.remove.RemoveMovieOutputBoundary;

/**
 * The Presenter for the Delete Watchlist Use Case.
 */
public class RemoveMoviePresenter implements RemoveMovieOutputBoundary {

    private final ViewManagerModel viewManagerModel;
    private final WatchlistViewModel watchlistViewModel;

    public RemoveMoviePresenter(ViewManagerModel viewManagerModel,
                                WatchlistViewModel watchlistViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.watchlistViewModel = watchlistViewModel;

    }

    /**
     * Updates the watchlist view after the movie is removed.
     * @param currentUser logged in user
     */
    @Override
    public void execute(User currentUser) {
        watchlistViewModel.getState().updateState(currentUser);
        watchlistViewModel.firePropertyChanged();
    }

    @Override
    public void execute(User currentUser, int watchlistIndex) {
        watchlistViewModel.getState().updateState(currentUser, watchlistIndex);
        watchlistViewModel.firePropertyChanged();
    }

}
