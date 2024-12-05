
package interface_adapter.movie;

import entity.CommonMovie;
import use_case.movie.MovieInputBoundary;
import use_case.movie.MovieInputData;
import java.util.List;


/**
 * Controller for the Movie Use Case.
 */
public class MovieController {
    private final MovieInputBoundary movieUseCaseInteractor;

    public MovieController(MovieInputBoundary movieUseCaseInteractor) {
        this.movieUseCaseInteractor = movieUseCaseInteractor;
    }

    /**
     * Executes the Movie Use Case.
     * @param movieName the title of the movie
     * @param results the List of Common Movies form the search results
     */
    public void execute(String movieName, List<CommonMovie> results) {
        final MovieInputData movieInputData = new MovieInputData(movieName, results);

        movieUseCaseInteractor.execute(movieInputData);
    }

    /**
     * Executes the "switch to HomeView" Use Case.
     */
    public void switchToHomeView() {
        movieUseCaseInteractor.switchToHomeView();
    }

    /**
     * Switch to the leaveReviewView.
     */
    public void switchToLeaveReviewView() {
        movieUseCaseInteractor.switchToLeaveReviewView();
    }
}
