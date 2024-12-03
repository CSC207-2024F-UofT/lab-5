package interface_adapter.watchlist;

import entity.User;
import interface_adapter.ViewManagerModel;
import interface_adapter.home.HomeViewModel;
import interface_adapter.search_results.SearchResultsViewModel;
import interface_adapter.watchlists.WatchlistsViewModel;
import use_case.watchlist.WatchlistOutputBoundary;

/**
 * The Presenter for the Signup Use Case.
 */
public class WatchlistPresenter implements WatchlistOutputBoundary {

    private final WatchlistsViewModel watchlistsViewModel;
    private final HomeViewModel homeViewModel;
    private final ViewManagerModel viewManagerModel;
    private final WatchlistViewModel watchlistViewModel;
    private final SearchResultsViewModel searchResultsViewModel;

    public WatchlistPresenter(ViewManagerModel viewManagerModel,
                              WatchlistsViewModel watchlistsViewModel, HomeViewModel homeViewModel,
                              WatchlistViewModel watchlistViewModel, SearchResultsViewModel searchResultsViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.homeViewModel = homeViewModel;
        this.watchlistsViewModel = watchlistsViewModel;
        this.watchlistViewModel = watchlistViewModel;
        this.searchResultsViewModel = searchResultsViewModel;

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
     */
    @Override
    public void switchToWatchlistsView(User currentUser) {

        viewManagerModel.setState(watchlistsViewModel.getViewName());
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
        viewManagerModel.setState(homeViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

    @Override
    public void switchToMovieSearchView(User currentUser) {
        // should switch to watchlist view which is not implemented
        viewManagerModel.setState(searchResultsViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }
}
