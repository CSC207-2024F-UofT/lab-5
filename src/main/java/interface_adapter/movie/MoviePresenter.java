package interface_adapter.movie;

import interface_adapter.ViewManagerModel;
import interface_adapter.home.HomeViewModel;
import use_case.movie.MovieOutputBoundary;

/**
 * The Presenter for the Login Use Case.
 */
public class MoviePresenter implements MovieOutputBoundary {

    private final MovieViewModel movieViewModel;
    private final ViewManagerModel viewManagerModel;
    private final HomeViewModel homeViewModel;

    public MoviePresenter(ViewManagerModel viewManagerModel,
                          MovieViewModel movieViewModel,
                          HomeViewModel homeViewModel) {
        this.movieViewModel = movieViewModel;
        this.viewManagerModel = viewManagerModel;
        this.homeViewModel = homeViewModel;
    }

    @Override
    public void switchToMovieView() {
        // On success, user clicks, switch to the movie view.
        viewManagerModel.setState(movieViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

    @Override
    public void switchToHomeView() {
        viewManagerModel.setState(homeViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

    @Override
    public void switchToUserReviewsView() {
        // TODO not implemented
        viewManagerModel.setState(homeViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

    public void switchToLeaveReviewView() {
        viewManagerModel.setState(movieViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }
}
