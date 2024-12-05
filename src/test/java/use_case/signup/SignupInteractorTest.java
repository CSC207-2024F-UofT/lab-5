package use_case.signup;

import data_access.InMemoryUserDataAccessObject;
import entity.CommonUserFactory;
import entity.User;
import entity.UserFactory;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class SignupInteractorTest {

    @Test
    void successTest() {
        SignupInputData inputData = new SignupInputData("Paul", "password", "password");
        SignupUserDataAccessInterface userRepository = new InMemoryUserDataAccessObject();

        // This creates a successPresenter that tests whether the test case is as we expect.
        SignupOutputBoundary successPresenter = new SignupOutputBoundary() {
            @Override
            public void prepareSuccessView(SignupOutputData user) {
                // 2 things to check: the output data is correct, and the user has been created in the DAO.
                assertEquals("Paul", user.getUsername());
                assertTrue(userRepository.existsByName("Paul"));
            }

            @Override
            public void prepareFailView(String error) {
                fail("Use case failure is unexpected.");
            }

            @Override
            public void switchToSurvey1View(String uname) {
                // This is expected
            }

            @Override
            public void switchToLoginView() {
                // This is expected
            }
        };

        SignupInputBoundary interactor = new SignupInteractor(userRepository, successPresenter);
        interactor.execute(inputData);
    }

    @Test
    void switchToSurvey1ViewTest() {
        SignupUserDataAccessInterface userRepository = new InMemoryUserDataAccessObject();

        // Create a presenter to test the switchToSurvey1View functionality.
        SignupOutputBoundary presenter = new SignupOutputBoundary() {
            @Override
            public void prepareSuccessView(SignupOutputData user) {
                fail("Success view preparation is unexpected.");
            }

            @Override
            public void prepareFailView(String error) {
                fail("Failure view preparation is unexpected.");
            }

            @Override
            public void switchToSurvey1View(String uname) {
                assertEquals("Paul", uname, "Switch to Survey1 view was called with the correct username.");
            }

            @Override
            public void switchToLoginView() {
                fail("Switch to Login view is unexpected.");
            }
        };

        SignupInputBoundary interactor = new SignupInteractor(userRepository, presenter);

        // Call the method to switch to the survey1 view.
        interactor.switchToSurvey1View("Paul");
    }

    @Test
    void switchToLoginViewTest() {
        SignupUserDataAccessInterface userRepository = new InMemoryUserDataAccessObject();

        // Create a presenter to test the switchToLoginView functionality.
        SignupOutputBoundary presenter = new SignupOutputBoundary() {
            @Override
            public void prepareSuccessView(SignupOutputData user) {
                fail("Success view preparation is unexpected.");
            }

            @Override
            public void prepareFailView(String error) {
                fail("Failure view preparation is unexpected.");
            }

            @Override
            public void switchToSurvey1View(String uname) {
                fail("Switch to Survey1 view is unexpected.");
            }

            @Override
            public void switchToLoginView() {
                // If this is called, the test passes.
                assertTrue(true, "Switch to Login view was called successfully.");
            }
        };

        SignupInputBoundary interactor = new SignupInteractor(userRepository, presenter);

        // Call the method to switch to the login view.
        interactor.switchToLoginView();
    }

    @Test
    void failurePasswordMismatchTest() {
        SignupInputData inputData = new SignupInputData("Paul", "password", "wrong");
        SignupUserDataAccessInterface userRepository = new InMemoryUserDataAccessObject();

        // This creates a presenter that tests whether the test case is as we expect.
        SignupOutputBoundary failurePresenter = new SignupOutputBoundary() {
            @Override
            public void prepareSuccessView(SignupOutputData user) {
                // this should never be reached since the test case should fail
                fail("Use case success is unexpected.");
            }

            @Override
            public void prepareFailView(String error) {
                assertEquals("Passwords don't match.", error);
            }

            @Override
            public void switchToSurvey1View(String uname) {
                // This is expected
            }

            @Override
            public void switchToLoginView() {
                // This is expected
            }
        };

        SignupInputBoundary interactor = new SignupInteractor(userRepository, failurePresenter);
        interactor.execute(inputData);
    }

    @Test
    void failureUserExistsTest() {
        SignupInputData inputData = new SignupInputData("Paul", "password", "wrong");
        SignupUserDataAccessInterface userRepository = new InMemoryUserDataAccessObject();

        // Add Paul to the repo so that when we check later they already exist
        UserFactory factory = new CommonUserFactory();
        User user = factory.create("Paul", "pwd");
        userRepository.save(user);

        // This creates a presenter that tests whether the test case is as we expect.
        SignupOutputBoundary failurePresenter = new SignupOutputBoundary() {
            @Override
            public void prepareSuccessView(SignupOutputData user) {
                // this should never be reached since the test case should fail
                fail("Use case success is unexpected.");
            }

            @Override
            public void prepareFailView(String error) {
                assertEquals("User already exists.", error);
            }

            @Override
            public void switchToSurvey1View(String uname) {
                // This is expected
            }

            @Override
            public void switchToLoginView() {
                // This is expected
            }
        };

        SignupInputBoundary interactor = new SignupInteractor(userRepository, failurePresenter);
        interactor.execute(inputData);
    }
}