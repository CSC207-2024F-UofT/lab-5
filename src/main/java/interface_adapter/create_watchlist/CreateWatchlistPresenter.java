
package interface_adapter.create_watchlist;

import entity.User;
import interface_adapter.ViewManagerModel;
import interface_adapter.watchlists.WatchlistsState;
import interface_adapter.watchlists.WatchlistsViewModel;
import use_case.create_watchlist.CreateWatchlistOutputBoundary;

/**
 * Presenter for Create Watchlist Use Case.
 */
public class CreateWatchlistPresenter implements CreateWatchlistOutputBoundary {
    private ViewManagerModel viewManagerModel;
    private WatchlistsViewModel watchlistsViewModel;

    public CreateWatchlistPresenter(ViewManagerModel viewManagerModel,
                                    WatchlistsViewModel watchlistsViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.watchlistsViewModel = watchlistsViewModel;
    }

    @Override
    public void prepareSuccessView(User user) {
        watchlistsViewModel.getState().setCurrentUser(user);
        viewManagerModel.setState(watchlistsViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
        watchlistsViewModel.firePropertyChanged();
    }

    @Override
    public void prepareFailView(String errorMessage) {
        final WatchlistsState watchlistsState = watchlistsViewModel.getState();
        watchlistsState.setEmptyListNameError(errorMessage);
        watchlistsViewModel.firePropertyChanged();
    }
}