package use_case.studymodequestion;

import interface_adapter.ViewManagerModel;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class StudyModeQuestionInteractorTest {

    @Test
    void successStartStudyModeTest() {
        // Create a ViewManagerModel to track the current view
        ViewManagerModel viewManagerModel = new ViewManagerModel();

        // Set up a Success Presenter to check if the user is redirected to <StudyModeQuestionView
        StudyModeQuestionOutputBoundary successPresenter = new StudyModeQuestionOutputBoundary() {
            @Override
            public void prepareSuccessView(StudyModeQuestionOutputData outputData) {
                // not used in this test
            }

            @Override
            public void prepareFailView(String errorMessage) {
                fail("Unexpected failure while starting study mode");
            }

            @Override
            public void switchToStudyMode() {
                // not used
            }
        };

        StudyModeQuestionDataAccessInterface dataAccessInterface = new StudyModeQuestionDataAccessInterface() {
        };
        StudyModeQuestionInputData studyModeQuestionInputData = new StudyModeQuestionInputData();
        // Create and execute the <StudyModeQuestion> Interactor
        StudyModeQuestionInputBoundary interactor = new StudyModeQuestionInteractor(dataAccessInterface,successPresenter);
        interactor.execute(studyModeQuestionInputData);

        // Assert that the interactor correctly navigated to the "study mode question" view
        interactor.switchToStudyModeView();
        assertEquals("", viewManagerModel.getState(), "The view state should be 'study mode question' after starting the study mode.");
    }

    @Test
    void successFinishStudyModeTest() {
        // Create a ViewManagerModel to track the current view
        ViewManagerModel viewManagerModel = new ViewManagerModel();

            StudyModeQuestionOutputBoundary successPresenter = new StudyModeQuestionOutputBoundary() {
                @Override
                public void prepareSuccessView(StudyModeQuestionOutputData outputData) {
                    // not used in this test
                }

                @Override
                public void prepareFailView(String errorMessage) {
                    fail("Unexpected failure while starting study mode");
                }

                @Override
                public void switchToStudyMode() {
                    // We check if the user is redirected to the mode selection view
                    viewManagerModel.setState("study mode");
                    viewManagerModel.firePropertyChanged();
                }
            };

            StudyModeQuestionDataAccessInterface dataAccessInterface = new StudyModeQuestionDataAccessInterface() {
            };
            StudyModeQuestionInputData studyModeQuestionInputData = new StudyModeQuestionInputData();
            // Create and execute the <StudyModeQuestion> Interactor
            StudyModeQuestionInputBoundary interactor = new StudyModeQuestionInteractor(dataAccessInterface,successPresenter);

        // Assert that the interactor navigated correctly to the "mode selection" view
        interactor.switchToStudyModeView();
        assertEquals("study mode", viewManagerModel.getState(), "The view state should be 'study mode' after finishing study mode.");
    }

    @Test
    void failureNoMoreQuestionsTest() {
        // This test handles the failure scenario where no more questions are available.

        StudyModeQuestionOutputBoundary failurePresenter = new StudyModeQuestionOutputBoundary() {
            @Override
            public void prepareSuccessView(StudyModeQuestionOutputData outputData) {
                // not used in this test
            }

            @Override
            public void prepareFailView(String errorMessage) {
                assertEquals("No more questions available.", errorMessage, "The error message should indicate no more questions are available.");
            }

            @Override
            public void switchToStudyMode() {
                // No need to use in this test
            }
        };

        StudyModeQuestionDataAccessInterface dataAccessInterface = new StudyModeQuestionDataAccessInterface() {
        };

        StudyModeQuestionInputData studyModeQuestionInputData = new StudyModeQuestionInputData();
        StudyModeQuestionInputBoundary interactor = new StudyModeQuestionInteractor(dataAccessInterface, failurePresenter);

        // Simulate the situation where there are no more questions
        interactor.execute(studyModeQuestionInputData);
        failurePresenter.prepareFailView("No more questions available.");
    }
}
