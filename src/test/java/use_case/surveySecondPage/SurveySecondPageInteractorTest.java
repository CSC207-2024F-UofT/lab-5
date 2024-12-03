package use_case.surveySecondPage;

import data_access.InMemoryUserDataAccessObject;
import entity.CommonUserFactory;
import entity.User;
import entity.UserFactory;
import org.junit.Test;
import use_case.survey_second_page.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class SurveySecondPageInteractorTest {
    @Test
    public void executeTest() {
        SurveySecondPageInputData surveySecondPageInputData = new SurveySecondPageInputData("Lion King", "Home Alone", "Frozen");
        InMemoryUserDataAccessObject userRepo = new InMemoryUserDataAccessObject();

        UserFactory factory = new CommonUserFactory();
        User user = factory.create("Alice", "password");
        userRepo.save(user);
        userRepo.setCurrentUsername("Alice");

        SurveySecondPageOutputBoundary surveySecondPagePresenter = new SurveySecondPageOutputBoundary() {

            @Override
            public void prepareSuccessView(SurveySecondPageOutputData surveySecondPageOutputData) {
                surveySecondPageOutputData.getFavoriteMovies().add("Lion King");
                surveySecondPageOutputData.getFavoriteMovies().add("Home Alone");
                surveySecondPageOutputData.getFavoriteMovies().add("Frozen");
                assertEquals("Lion King", surveySecondPageOutputData.getFavoriteMovies().get(0));
                assertEquals("Home Alone", surveySecondPageOutputData.getFavoriteMovies().get(1));
                assertEquals("Frozen", surveySecondPageOutputData.getFavoriteMovies().get(2));
            }

            @Override
            public void prepareFailView(String error) {fail("Use case failure is unexpected.");}

            @Override
            public void switchToHomeView() {
                fail("Switching to home view is unexpected.");
            }
        };
        SurveySecondPageInputBoundary surveySecondPageInteractor =
                new SurveySecondPageInteractor(userRepo, surveySecondPagePresenter, new CommonUserFactory());
        surveySecondPageInteractor.execute(surveySecondPageInputData);
    }

    @Test
    public void executeWithNonExistentFirstMovie() {
        SurveySecondPageInputData surveySecondPageInputData = new SurveySecondPageInputData("", "movie2", "movie3");
        InMemoryUserDataAccessObject userRepo = new InMemoryUserDataAccessObject();

        UserFactory factory = new CommonUserFactory();
        User user = factory.create("Alice", "password");
        userRepo.save(user);
        userRepo.setCurrentUsername("Alice");

        SurveySecondPageOutputBoundary surveySecondPagePresenter = new SurveySecondPageOutputBoundary() {

            @Override
            public void prepareSuccessView(SurveySecondPageOutputData surveySecondPageOutputData) {
                fail("Use case success is unexpected.");
            }

            @Override
            public void prepareFailView(String error) {
                assertEquals("nonExistentMovie: Movie not found.", error);
            }

            @Override
            public void switchToHomeView() {
                fail("Switching to home view is unexpected.");
            }
        };
        SurveySecondPageInputBoundary surveySecondPageInteractor =
                new SurveySecondPageInteractor(userRepo, surveySecondPagePresenter, new CommonUserFactory());
        surveySecondPageInteractor.execute(surveySecondPageInputData);
    }

    @Test
    public void executeWithNonExistentSecondMovie() {
        SurveySecondPageInputData surveySecondPageInputData = new SurveySecondPageInputData("movie1", "", "movie3");
        InMemoryUserDataAccessObject userRepo = new InMemoryUserDataAccessObject();

        UserFactory factory = new CommonUserFactory();
        User user = factory.create("Alice", "password");
        userRepo.save(user);
        userRepo.setCurrentUsername("Alice");

        SurveySecondPageOutputBoundary surveySecondPagePresenter = new SurveySecondPageOutputBoundary() {

            @Override
            public void prepareSuccessView(SurveySecondPageOutputData surveySecondPageOutputData) {
                fail("Use case success is unexpected.");
            }

            @Override
            public void prepareFailView(String error) {
                assertEquals("nonExistentMovie: Movie not found.", error);
            }

            @Override
            public void switchToHomeView() {

            }
        };
        SurveySecondPageInputBoundary surveySecondPageInteractor =
                new SurveySecondPageInteractor(userRepo, surveySecondPagePresenter, new CommonUserFactory());
        surveySecondPageInteractor.execute(surveySecondPageInputData);
    }

    @Test
    public void executeWithNonExistentThirdMovie() {
        SurveySecondPageInputData surveySecondPageInputData = new SurveySecondPageInputData("movie1", "movie2", "");
        InMemoryUserDataAccessObject userRepo = new InMemoryUserDataAccessObject();

        UserFactory factory = new CommonUserFactory();
        User user = factory.create("Alice", "password");
        userRepo.save(user);
        userRepo.setCurrentUsername("Alice");

        SurveySecondPageOutputBoundary surveySecondPagePresenter = new SurveySecondPageOutputBoundary() {

            @Override
            public void prepareSuccessView(SurveySecondPageOutputData surveySecondPageOutputData) {
                fail("Use case success is unexpected.");
            }

            @Override
            public void prepareFailView(String error) {
                assertEquals("nonExistentMovie: Movie not found.", error);
            }

            @Override
            public void switchToHomeView() {

            }
        };
        SurveySecondPageInputBoundary surveySecondPageInteractor =
                new SurveySecondPageInteractor(userRepo, surveySecondPagePresenter, new CommonUserFactory());
        surveySecondPageInteractor.execute(surveySecondPageInputData);
    }

    @Test
    public void executeWithAllNonExistentMovies() {
        SurveySecondPageInputData surveySecondPageInputData = new SurveySecondPageInputData("", "", "");
        InMemoryUserDataAccessObject userRepo = new InMemoryUserDataAccessObject();

        UserFactory factory = new CommonUserFactory();
        User user = factory.create("Alice", "password");
        userRepo.save(user);
        userRepo.setCurrentUsername("Alice");

        SurveySecondPageOutputBoundary surveySecondPagePresenter = new SurveySecondPageOutputBoundary() {

            @Override
            public void prepareSuccessView(SurveySecondPageOutputData surveySecondPageOutputData) {
                fail("Use case success is unexpected.");
            }

            @Override
            public void prepareFailView(String error) {
                assertEquals("nonExistentMovie: Movie not found.", error);
            }

            @Override
            public void switchToHomeView() {

            }
        };
        SurveySecondPageInputBoundary surveySecondPageInteractor =
                new SurveySecondPageInteractor(userRepo, surveySecondPagePresenter, new CommonUserFactory());
        surveySecondPageInteractor.execute(surveySecondPageInputData);
    }

    @Test
    public void switchToHomeViewTest() {
        InMemoryUserDataAccessObject userRepo = new InMemoryUserDataAccessObject();
        SurveySecondPageOutputBoundary surveySecondPagePresenter = new SurveySecondPageOutputBoundary() {

            @Override
            public void prepareSuccessView(SurveySecondPageOutputData surveySecondPageOutputData) {
                fail("Use case success is unexpected.");
            }

            @Override
            public void prepareFailView(String error) {
                fail("Use case failure is unexpected.");
            }

            @Override
            public void switchToHomeView() {
                assertEquals("Switching to home view.", "Switching to home view.");
            }
        };
        SurveySecondPageInputBoundary surveySecondPageInteractor =
                new SurveySecondPageInteractor(userRepo, surveySecondPagePresenter, new CommonUserFactory());
        surveySecondPageInteractor.switchToHomeView();
    }
}
