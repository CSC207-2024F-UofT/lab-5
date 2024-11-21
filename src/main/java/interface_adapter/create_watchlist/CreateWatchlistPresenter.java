package interface_adapter.create_watchlist;

import interface_adapter.ViewManagerModel;
import interface_adapter.watchlists.WatchlistsState;
import interface_adapter.watchlists.WatchlistsViewModel;
import use_case.create_watchlist.CreateWatchlistOutputData;

/**
 * The presenter for the Create Watchlist Use Case.
 */
public class CreateWatchlistPresenter {
    private ViewManagerModel viewManagerModel;
    private WatchlistsViewModel watchlistsViewModelViewModel;

    public CreateWatchlistPresenter(ViewManagerModel viewManagerModel,
                                   WatchlistsViewModel watchlistsViewModelViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.watchlistsViewModelViewModel = watchlistsViewModelViewModel;
    }

    @Override
    public void prepareSuccessView(CreateWatchlistOutputData outputData) {
        final WatchlistsState watchlistsState = watchlistsViewModelViewModel.getState();
        watchlistsViewModelViewModel.setState(watchlistsState);
    }

    @Override
    public void prepareFailView(String errorMessage) {
        final WatchlistsState watchlistsState = watchlistsViewModelViewModel.getState();
        watchlistsViewModelViewModel.setState(watchlistsState);
    }
}
