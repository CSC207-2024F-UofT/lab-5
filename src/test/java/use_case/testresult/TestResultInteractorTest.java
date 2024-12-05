package use_case.testresult;

import data_access.InMemoryTestResultDataAccessObject;
import interface_adapter.ViewManagerModel;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class TestResultInteractorTest {

    @Test
    void successAllCorrectTestResultTest() {
        ArrayList<String> incorrectQuestions = new ArrayList<>();
        TestresultInputData inputData = new TestresultInputData(40, incorrectQuestions);

        TestresultDataAccessInterface testResultDataAccess = new InMemoryTestResultDataAccessObject();

        // This creates a successPresenter that tests whether the test result is presented correctly
        // In this case, the test result include no incorrect questions and the user took 120 seconds
        TestresultOutputBoundary successPresenter = new TestresultOutputBoundary() {
            @Override
            public void prepareSuccessView(TestresultOutputData outputData) {
                assertEquals(40, outputData.getCorrectQuestions());
                assertEquals(0, incorrectQuestions.size());
            }

            @Override
            public void prepareFailView(String errorMessage) {
                fail("value present on test result is not correct");
            }

            @Override
            public void switchToLoggedInView() {
                // no need to implement in a test
            }
        };

        // Create the test result interactor and present
        // the number of correct questions, time, and a list of incorect questions
        TestresultInputBoundary interactor = new TestresultInteractor(testResultDataAccess, successPresenter);
        interactor.execute(inputData);

        assertEquals(40, testResultDataAccess.getCorrectQuestions());
        assertEquals(0, testResultDataAccess.getIncorrectQuestions().size());
    }

    @Test
    void successCorrectTestResultTest() {
        ArrayList<String> incorrectQuestions = new ArrayList<>(Arrays.asList("Q1", "Q2"));
        TestresultInputData inputData = new TestresultInputData(38, incorrectQuestions);

        TestresultDataAccessInterface testResultDataAccess = new InMemoryTestResultDataAccessObject();

        // This creates a successPresenter that tests whether the test result is presented correctly
        // In this case, the test result include 2 incorrect questions and the user took 100 seconds
        // This test also test if the incorrect questions are being called correctly
        TestresultOutputBoundary successPresenter = new TestresultOutputBoundary() {
            @Override
            public void prepareSuccessView(TestresultOutputData outputData) {
                // Validate that the presenter is called with correct data
                assertEquals(38, outputData.getCorrectQuestions());
                assertEquals(incorrectQuestions, outputData.getIncorrectQuestions());
            }

            @Override
            public void prepareFailView(String errorMessage) {
                fail("Expected success, but fail view was prepared");
            }

            @Override
            public void switchToLoggedInView() {
                // No need for test
            }
        };

        // Create and execute the interactor
        TestresultInputBoundary interactor = new TestresultInteractor(testResultDataAccess, successPresenter);
        interactor.execute(inputData);
        assertEquals(38, testResultDataAccess.getCorrectQuestions());
        assertEquals(incorrectQuestions, testResultDataAccess.getIncorrectQuestions());
    }

    @Test
    void failureInvalidCorrectQuestionTest() {
        ArrayList<String> incorrectQuestions = new ArrayList<>(Arrays.asList("Q1", "Q2"));
        TestresultInputData inputData = new TestresultInputData(-1, incorrectQuestions);

        TestresultDataAccessInterface testResultDataAccess = new InMemoryTestResultDataAccessObject();

        // This creates a successPresenter that tests whether the test result is presented correctly
        // In this case, the test result include 2 incorrect questions and the user took 100 seconds
        // This test also test if the incorrect questions are being called correctly
        TestresultOutputBoundary successPresenter = new TestresultOutputBoundary() {
            @Override
            public void prepareSuccessView(TestresultOutputData outputData) {
                fail("Expected failure, but success view was prepared");
            }

            @Override
            public void prepareFailView(String errorMessage) {
                assertEquals("Invalid test result provided", errorMessage);
            }

            @Override
            public void switchToLoggedInView() {
                // No need for implementation in test
            }
        };

        // Create and execute the interactor
        TestresultInputBoundary interactor = new TestresultInteractor(testResultDataAccess, successPresenter);
        interactor.execute(inputData);
        // assert the correct questions to zero because no data should be passed to data access object
        // it's the default value of int for correctQuestions and time and an empty arraylist for incorrectQuestions
        assertEquals(0, testResultDataAccess.getCorrectQuestions());
        assertEquals(0, testResultDataAccess.getIncorrectQuestions().size());
    }


    @Test
    void switchToLoggedInViewTest() {
        // Arrange the input data
        ArrayList<String> incorrectQuestions = new ArrayList<>(Arrays.asList("Q1", "Q2"));
        TestresultInputData inputData = new TestresultInputData(38, incorrectQuestions);

        TestresultDataAccessInterface testResultDataAccess = new InMemoryTestResultDataAccessObject();

        // Create a ViewManagerModel to track the current view
        ViewManagerModel viewManagerModel = new ViewManagerModel();

        // Create a presenter with the overridden method that interacts with the ViewManagerModel
        TestresultOutputBoundary successPresenter = new TestresultOutputBoundary() {
            @Override
            public void prepareSuccessView(TestresultOutputData outputData) {
                // No action needed for this specific test
            }

            @Override
            public void prepareFailView(String errorMessage) {
                // No action needed for this specific test
            }

            @Override
            public void switchToLoggedInView() {
                //same implementation from the test result presenter switchToLoggedInView()
                viewManagerModel.setState("logged in");
                viewManagerModel.firePropertyChanged();
            }
        };

            // Create and execute the interactor
            TestresultInputBoundary interactor = new TestresultInteractor(testResultDataAccess, successPresenter);
            interactor.execute(inputData);

            //Test if the view is navigated successfuly
            interactor.switchToLoggedInView();
            assertEquals("logged in", viewManagerModel.getState());

    }
}