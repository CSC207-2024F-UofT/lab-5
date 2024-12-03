package use_case.movie;

import entity.CommonMovie;
import entity.Movie;

import java.util.List;

/**
 * The Movie Interactor.
 */
public class MovieInteractor implements MovieInputBoundary {
    private final MovieUserDataAccessInterface movieDataAccessObject;
    private final MovieOutputBoundary moviePresenter;

    public MovieInteractor(MovieUserDataAccessInterface movieDataAccessInterface,
                                MovieOutputBoundary movieOutputBoundary) {
        this.movieDataAccessObject = movieDataAccessInterface;
        this.moviePresenter = movieOutputBoundary;
    }

    @Override
    public void execute(MovieInputData movieInputData) {
        final String movieTitle = movieInputData.getMovieTitle();
        final List<CommonMovie> results = movieInputData.getResults();

//        CommonMovie searchedMovie = null;

        for (int i = 0; i < results.size(); i++) {
            if (results.get(i).getTitle().equals(movieTitle)) {
                CommonMovie searchedMovie = results.get(i);
            }
        }

//        final MovieOutputData movieOutputData = new MovieOutputData(movieTitle,
//                posterPath, overview, voteAverage, genres);
        // moviePresenter.switchToMovieView(movieOutputData);
        moviePresenter.switchToMovieView();
    }

    @Override
    public void switchToHomeView() {
//        moviePresenter.switchToHomeView();
    }

    public void switchToLeaveReviewView() {
        moviePresenter.switchToLeaveReviewView();
    }
}
