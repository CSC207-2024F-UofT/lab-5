package use_case.movie;

import data_access.APIMovieAccess;
import data_access.DBMovieDataAccessObject;
import entity.CommonMovie;
import entity.CommonMovieFactory;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class MovieInteractorTest {

    @Test
    void successTest(){
        final String movieTitle = "Home Alone";
        final List<CommonMovie> results = APIMovieAccess.formatedSearchedMovies(movieTitle);

        MovieInputData movieInputData = new MovieInputData(movieTitle, results);

        MovieOutputBoundary moviePresenter = new MovieOutputBoundary() {
            @Override
            public void prepareSuccessView(MovieOutputData movieOutputData) {
                // This method should be called in the test case.
                final String movieName = "Home Alone";
                final String posterPath = "/onTSipZ8R3bliBdKfPtsDuHTdlL.jpg";
                final String overview = "Eight-year-old Kevin McCallister makes the most of the situation after his " +
                        "family unwittingly leaves him behind when they go on Christmas vacation. But when a pair of " +
                        "bungling burglars set their sights on Kevin's house, the plucky kid stands ready to defend " +
                        "his territory. By planting booby traps galore, adorably mischievous Kevin stands his ground " +
                        "as his frantic mother attempts to race home before Christmas Day.";
                final String voterAverage = "7.437";

                // Assertions to validate the MovieOutputData getters
                assertEquals(movieName, movieOutputData.getMovieName());
                assertEquals(posterPath, movieOutputData.getPosterPath());
                assertEquals(overview, movieOutputData.getOverview());
                assertEquals(voterAverage, movieOutputData.getVoteAverage());
                assertEquals(Arrays.asList("Comedy", "Family"), movieOutputData.getGenres());
            }

            @Override
            public void switchToHomeView() {
                fail("switchToHomeView is not the method that should be called");
            }

            @Override
            public void switchToLeaveReviewView() {
                fail("switchToLeaveReviewView is not the method that should be called");
            }
        };

        MovieInputBoundary interactor = new MovieInteractor(moviePresenter);
        interactor.execute(movieInputData);
    }

    @Test
    void switchToHomeViewTest(){
        final CommonMovieFactory movieFactory = new CommonMovieFactory();

        MovieOutputBoundary moviePresenter = new MovieOutputBoundary() {
            @Override
            public void prepareSuccessView(MovieOutputData movieOutputData) {
                fail("prepareSuccessView is not the method that should be called");
            }

            @Override
            public void switchToHomeView() {
                // This method should be called in the test case.
            }

            @Override
            public void switchToLeaveReviewView() {
                fail("switchToLeaveReviewView is not the method that should be called");
            }
        };

        MovieInputBoundary interactor = new MovieInteractor(moviePresenter);
        interactor.switchToHomeView();
    }

    @Test
    void switchToLeaveReviewViewTest(){
        final CommonMovieFactory movieFactory = new CommonMovieFactory();

        MovieOutputBoundary moviePresenter = new MovieOutputBoundary() {
            @Override
            public void prepareSuccessView(MovieOutputData movieOutputData) {
                fail("prepareSuccessView is not the method that should be called");
            }

            @Override
            public void switchToHomeView() {
                fail("switchToHomeView is not the method that should be called");
            }

            @Override
            public void switchToLeaveReviewView() {
                // This method should be called in the test case.

            }
        };

        MovieInputBoundary interactor = new MovieInteractor(moviePresenter);
        interactor.switchToLeaveReviewView();
    }

}
