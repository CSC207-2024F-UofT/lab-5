
package interface_adapter.add_to_watchlist;

import entity.User;
import interface_adapter.ViewManagerModel;
import interface_adapter.movie.MovieState;
import interface_adapter.movie.MovieViewModel;
import use_case.add_to_watchlist.AddToWatchlistOutputBoundary;

/**
 * The presenter for the Add To Watchlist.
 */
public class AddToWatchlistPresenter implements AddToWatchlistOutputBoundary {

    private final ViewManagerModel viewManagerModel;
    private final MovieViewModel movieViewModel;

    public AddToWatchlistPresenter(ViewManagerModel viewManagerModel,
                                   MovieViewModel movieViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.movieViewModel = movieViewModel;
    }

    @Override
    public void prepareSuccessView(User user) {
        movieViewModel.getState().setCurrentUser(user);
        viewManagerModel.setState(movieViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
        movieViewModel.firePropertyChanged();
    }

    @Override
    public void prepareFailView(String errorMessage) {
        final MovieState movieState = movieViewModel.getState();
        movieState.setMovieError(errorMessage);
        movieViewModel.firePropertyChanged();
    }
}