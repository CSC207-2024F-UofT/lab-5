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

        for (int i = 0; i < results.size(); i++) {
            if (results.get(i).getTitle().equals(movieTitle)) {
                // get and store the data
                final CommonMovie searchedMovie = results.get(i);
                final String movieName = searchedMovie.getTitle();
                final String posterPath = searchedMovie.getPoster();
                final String overview = searchedMovie.getOverview();
                final String voteAverage = searchedMovie.getVoterAverage();
                final List<String> genres = searchedMovie.getGenres();

                final MovieOutputData movieOutputData = new MovieOutputData(movieName,
                        posterPath, overview, voteAverage, genres);

                moviePresenter.prepareSuccessView(movieOutputData);

            }
        }
    }

    @Override
    public void switchToHomeView() {
        moviePresenter.switchToHomeView();
    }

    public void switchToLeaveReviewView() {
        moviePresenter.switchToLeaveReviewView();
    }
}
