package interface_adapter.watchlist;

import entity.Movie;
import entity.User;
import interface_adapter.ViewManagerModel;
import interface_adapter.home.HomeViewModel;
import interface_adapter.movie.MovieViewModel;
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
    private final MovieViewModel movieViewModel;

    public WatchlistPresenter(ViewManagerModel viewManagerModel,
                              WatchlistsViewModel watchlistsViewModel, HomeViewModel homeViewModel,
                              WatchlistViewModel watchlistViewModel, SearchResultsViewModel searchResultsViewModel, MovieViewModel movieViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.homeViewModel = homeViewModel;
        this.watchlistsViewModel = watchlistsViewModel;
        this.watchlistViewModel = watchlistViewModel;
        this.searchResultsViewModel = searchResultsViewModel;
        this.movieViewModel = movieViewModel;

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

    @Override
    public void switchToMovieSearchView(String currentUser) {
        searchResultsViewModel.getState().setUsername(currentUser);
        viewManagerModel.setState(searchResultsViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

    /**
     * Executes the "switch to MovieView" Use Case.
     *
     * @param aCurrentUser  logged in user
     * @param aCurrentMovie current movie
     * @param watchedData   if user watched a movie
     */
    @Override
    public void switchToMovieView(User aCurrentUser, Movie aCurrentMovie, boolean watchedData) {
        movieViewModel.getState().update(aCurrentUser, aCurrentMovie, watchedData);
        movieViewModel.firePropertyChanged();
        viewManagerModel.setState(movieViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

}
