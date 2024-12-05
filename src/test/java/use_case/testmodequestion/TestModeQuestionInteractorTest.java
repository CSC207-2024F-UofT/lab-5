package use_case.testmodequestion;

import interface_adapter.ViewManagerModel;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

public class TestModeQuestionInteractorTest {

    @Test
    void successStartTestModeTest() {
        // Create a ViewManagerModel to track the current view
        ViewManagerModel viewManagerModel = new ViewManagerModel();
        ArrayList<String> incorrectQuestions = new ArrayList<>();

        // Set up a Success Presenter to check if the user is redirected to <StudyModeQuestionView
        TestModeQuestionOutputBoundary successPresenter = new TestModeQuestionOutputBoundary() {
            @Override
            public void prepareSuccessView(TestModeQuestionOutputData outputData) {
                assertEquals(3, outputData.getCorrectQuestions());
                assertEquals(0, incorrectQuestions.size());            }

            @Override
            public void prepareFailView(String errorMessage) {
                fail("Unexpected failure while starting test mode");
            }

            @Override
            public void switchToTestResultView() {
                // not used in this case
            }
        };

        TestModeQuestionDataAccessInterface dataAccessInterface = new TestModeQuestionDataAccessInterface() {
            @Override
            public int getCorrectQuestions() {
                return 3;
            }

            @Override
            public void setCorrectQuestions(int correctQuestions) {

            }

            @Override
            public ArrayList<String> getIncorrectQuestions() {
                return  new ArrayList<>();
            }

            @Override
            public void setIncorrectQuestions(ArrayList<String> incorrectQuestions) {

            }
        };
        TestModeQuestionInputData studyModeQuestionInputData = new TestModeQuestionInputData(3,incorrectQuestions);
        // Create and execute the <StudyModeQuestion> Interactor
        TestModeQuestionInputBoundary interactor = new TestModeQuestionInteractor(dataAccessInterface,successPresenter);
        interactor.execute(studyModeQuestionInputData);

        // Assert that the interactor correctly navigated to the "study mode question" view
        assertEquals(3, dataAccessInterface.getCorrectQuestions());
        assertEquals(0, dataAccessInterface.getIncorrectQuestions().size());    }

    @Test
    void successSwitchTestResultTest() {
        // Create a ViewManagerModel to track the current view
        ViewManagerModel viewManagerModel = new ViewManagerModel();
        ArrayList<String> incorrectQuestions = new ArrayList<>();

        // Set up a Success Presenter to check if the user is redirected to <StudyModeQuestionView
        TestModeQuestionOutputBoundary successPresenter = new TestModeQuestionOutputBoundary() {
            @Override
            public void prepareSuccessView(TestModeQuestionOutputData outputData) {
//                assertEquals(3, outputData.getCorrectQuestions());
//                assertEquals(0, incorrectQuestions.size());
                }

            @Override
            public void prepareFailView(String errorMessage) {
                fail("Unexpected failure while starting test mode");
            }

            @Override
            public void switchToTestResultView() {
                viewManagerModel.setState("test result");
                viewManagerModel.firePropertyChanged();            }
        };

        TestModeQuestionDataAccessInterface dataAccessInterface = new TestModeQuestionDataAccessInterface() {
            @Override
            public int getCorrectQuestions() {
                return 3;
            }

            @Override
            public void setCorrectQuestions(int correctQuestions) {

            }

            @Override
            public ArrayList<String> getIncorrectQuestions() {
                return  new ArrayList<>();
            }

            @Override
            public void setIncorrectQuestions(ArrayList<String> incorrectQuestions) {

            }
        };
        TestModeQuestionInputData testModeQuestionInputData = new TestModeQuestionInputData(3,incorrectQuestions);
        // Create and execute the <StudyModeQuestion> Interactor
        TestModeQuestionInputBoundary interactor = new TestModeQuestionInteractor(dataAccessInterface,successPresenter);

        // Assert that the interactor correctly navigated to the "study mode question" view
        interactor.switchToTestResultView();
        assertEquals("test result", viewManagerModel.getState(), "The view state should be 'test result' after finishing test mode.");
        assertEquals(3, dataAccessInterface.getCorrectQuestions());
        assertEquals(0, dataAccessInterface.getIncorrectQuestions().size());
    }

    @Test
    void failureNoMoreQuestionsTest() {
        // This test handles the failure scenario where no more questions are available.
        ArrayList<String> incorrectQuestions = new ArrayList<>();

        TestModeQuestionOutputBoundary failurePresenter = new TestModeQuestionOutputBoundary() {
            @Override
            public void prepareSuccessView(TestModeQuestionOutputData outputData) {
                assertEquals(0, outputData.getIncorrectQuestions().size());
            }

            @Override
            public void prepareFailView(String errorMessage) {
                assertEquals("Invalid correct question.", errorMessage,
                        "The error message should indicate the number of correct questions are invalid.");
            }

            @Override
            public void switchToTestResultView() {
                // not used
            }
        };


        TestModeQuestionDataAccessInterface dataAccessInterface = new TestModeQuestionDataAccessInterface() {
            @Override
            public int getCorrectQuestions() {
                return 0;
            }

            @Override
            public void setCorrectQuestions(int correctQuestions) {

            }

            @Override
            public ArrayList<String> getIncorrectQuestions() {
                return incorrectQuestions;
            }

            @Override
            public void setIncorrectQuestions(ArrayList<String> incorrectQuestions) {

            }
        };

        TestModeQuestionInputData testModeQuestionInputData = new TestModeQuestionInputData(-1, incorrectQuestions);
        TestModeQuestionInputBoundary interactor = new TestModeQuestionInteractor(dataAccessInterface, failurePresenter);

        // Simulate the situation where there are no more questions
        interactor.execute(testModeQuestionInputData);
        failurePresenter.prepareFailView("Invalid correct question.");
        assertEquals(0, dataAccessInterface.getCorrectQuestions());
        assertEquals(0, dataAccessInterface.getIncorrectQuestions().size());
    }
}
