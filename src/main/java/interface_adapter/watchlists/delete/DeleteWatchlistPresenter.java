package interface_adapter.watchlists.delete;

import interface_adapter.ViewManagerModel;
import interface_adapter.watchlists.WatchlistsViewModel;
import use_case.watchlists.delete.DeleteWatchlistOutputBoundary;
import use_case.watchlists.rename.RenameOutputBoundary;

/**
 * The Presenter for the Delete Watchlist Use Case.
 */
public class DeleteWatchlistPresenter implements DeleteWatchlistOutputBoundary {

    private final ViewManagerModel viewManagerModel;
    private final WatchlistsViewModel watchlistsViewModel;

    public DeleteWatchlistPresenter(ViewManagerModel viewManagerModel,
                                    WatchlistsViewModel watchlistsViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.watchlistsViewModel = watchlistsViewModel;

    }

    /**
     * Updates the watchlists view after the watchlist is renamed.
     */
    @Override
    public void execute() {
        watchlistsViewModel.firePropertyChanged();
    }
}
