package interface_adapter.watchlists;

import entity.User;
import interface_adapter.ViewManagerModel;
import interface_adapter.home.HomeViewModel;
import interface_adapter.watchlist.WatchlistViewModel;
import use_case.watchlists.WatchlistsOutputBoundary;

/**
 * The Presenter for the Signup Use Case.
 */
public class WatchlistsPresenter implements WatchlistsOutputBoundary {

    private final WatchlistsViewModel watchlistsViewModel;
    private final HomeViewModel homeViewModel;
    private final ViewManagerModel viewManagerModel;
    private final WatchlistViewModel watchlistViewModel;

    public WatchlistsPresenter(ViewManagerModel viewManagerModel,
                               WatchlistsViewModel watchlistsViewModel, HomeViewModel homeViewModel, WatchlistViewModel watchlistViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.homeViewModel = homeViewModel;
        this.watchlistsViewModel = watchlistsViewModel;
        this.watchlistViewModel = watchlistViewModel;

    }

    @Override
    public void switchToHomeView() {
        viewManagerModel.setState(homeViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

    /**
     * Switches to the Watchlist View.
     *
     * @param currentUser user that is currently logged in
     * @param ind         index that corresponds to the watchlist to switch to
     */
    @Override
    public void switchToWatchlistView(User currentUser, int ind) {
        // should switch to watchlist view which is not implemented
        watchlistViewModel.getState().updateState(currentUser, ind);
        watchlistViewModel.firePropertyChanged();
        viewManagerModel.setState(watchlistViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

    /**
     * Switches to the PWL View.
     *
     * @param currentUser user that is currently logged in
     */
    @Override
    public void switchToPWLView(User currentUser) {
        // should switch to watchlist view which is not implemented
        watchlistViewModel.getState().updateState(currentUser);
        watchlistViewModel.firePropertyChanged();
        viewManagerModel.setState(watchlistViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }
}
