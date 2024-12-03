package interface_adapter.movie;

import interface_adapter.ViewManagerModel;
import interface_adapter.home.HomeViewModel;
import interface_adapter.leave_review.LeaveReviewViewModel;
import use_case.movie.MovieOutputBoundary;
import use_case.movie.MovieOutputData;
import view.LeaveReviewView;

/**
 * The Presenter for the Login Use Case.
 */
public class MoviePresenter implements MovieOutputBoundary {

    private final MovieViewModel movieViewModel;
    private final ViewManagerModel viewManagerModel;
    private final HomeViewModel homeViewModel;
    private final LeaveReviewViewModel leaveReviewViewModel;

    public MoviePresenter(ViewManagerModel viewManagerModel,
                          MovieViewModel movieViewModel,
                          HomeViewModel homeViewModel, LeaveReviewViewModel leaveReviewViewModel) {
        this.movieViewModel = movieViewModel;
        this.viewManagerModel = viewManagerModel;
        this.homeViewModel = homeViewModel;
        this.leaveReviewViewModel = leaveReviewViewModel;
    }

    @Override
    public void prepareSuccessView(MovieOutputData movieOutputData) {
        // On success, when the user clicks see more, the movie panel should be updated

        // Access the current state from the ViewModel
        final MovieState movieState = movieViewModel.getState();

        // Update the state
        movieState.setTitle(movieOutputData.getMovieName());
        movieState.setPosterPath(movieOutputData.getPosterPath());
        movieState.setOverview(movieOutputData.getOverview());
        movieState.setVoteAverage(movieOutputData.getVoteAverage());
        movieState.setGenres(movieOutputData.getGenres());

        movieViewModel.firePropertyChanged();
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
        System.out.println("Presenter reached");
        leaveReviewViewModel.getState().setUsername(movieViewModel.getState().getCurrentUser().getName());
        leaveReviewViewModel.getState().setMoviename(movieViewModel.getState().getCurrentMovie().getTitle());
        viewManagerModel.setState(leaveReviewViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }
}
