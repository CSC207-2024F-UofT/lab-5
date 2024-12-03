package interface_adapter.watchlists.rename;

import entity.User;
import interface_adapter.ViewManagerModel;
import interface_adapter.home.HomeViewModel;
import interface_adapter.watchlists.WatchlistsViewModel;
import use_case.home.HomeOutputBoundary;
import use_case.watchlists.rename.RenameOutputBoundary;

/**
 * The Presenter for the Change Password Use Case.
 */
public class RenamePresenter implements RenameOutputBoundary {

    private final ViewManagerModel viewManagerModel;
    private final WatchlistsViewModel watchlistsViewModel;

    public RenamePresenter(ViewManagerModel viewManagerModel,
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
