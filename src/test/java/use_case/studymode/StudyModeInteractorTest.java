package use_case.studymode;

import interface_adapter.ViewManagerModel;
import org.junit.jupiter.api.Test;
import data_access.InMemoryStudyModeDataAccessInterface;

import static org.junit.jupiter.api.Assertions.*;

class StudyModeInteractorTest {

    @Test
    void successModuleSelectionTest() {
        // assume user selected module 1 to study
        StudyModeInputData inputData = new StudyModeInputData("Module 1");

        // Set up the In-Memory Data
        StudyModeDataAccessInterface studymodeDataAccessInterface = new InMemoryStudyModeDataAccessInterface();

        // Create a ViewManagerModel to track the current view
        ViewManagerModel viewManagerModel = new ViewManagerModel();

        // Set up a Success Presenter to check if the user is redirected to <StudyModeBeginView>
        // with the module name they selected. In this case, the module name is "Module 1".
        StudyModeOutputBoundary successPresenter = new StudyModeOutputBoundary() {
            @Override
            public void prepareSuccessView(StudyModeOutputData outputData) {
                assertEquals("Module 1", outputData.getModule());
            }

            @Override
            public void prepareFailView(String errorMessage) {
                fail("Unexpected failure while selecting module");
            }

            @Override
            public void switchToStudyModeBeginView() {
                viewManagerModel.setState("study mode begin");
                viewManagerModel.firePropertyChanged();
            }

            @Override
            public void switchToModeSelectionView() {
                // Not useful in this test
            }
        };

        // Create and execute the study mode interactor
        StudyModeInputBoundary interactor = new StudyModeInteractor(studymodeDataAccessInterface, successPresenter);
        interactor.execute(inputData);

        // Test if the module name was saved
        assertEquals("Module 1", studymodeDataAccessInterface.getModule());

        // Test if the view is navigated successfully
        interactor.switchToStudyModeBeginView();
        assertEquals("study mode begin", viewManagerModel.getState());
    }

    @Test
    void failureIncorrectRedirectionTest() {
        // Create input data for selecting Module 1
        StudyModeInputData inputData = new StudyModeInputData("Module 1");

        // Set up Im-Memory Data Access and Output Boundary
        StudyModeDataAccessInterface studymodeDataAccessInterface = new InMemoryStudyModeDataAccessInterface();
        StudyModeOutputBoundary failurePresenter = new StudyModeOutputBoundary() {
            @Override
            public void prepareSuccessView(StudyModeOutputData outputData) {
                // Simulate incorrect redirection by forcing an incorrect state
                assertNotEquals("Module 2", outputData.getModule());
            }

            @Override
            public void prepareFailView(String errorMessage) {
                fail("Unexpected failure while selecting module.");
            }

            @Override
            public void switchToStudyModeBeginView() {
                // Not useful in this test
            }

            @Override
            public void switchToModeSelectionView() {
                // Not useful in this test
            }
        };

        // Create the <StudyModeInteractor> and execute the selection
        StudyModeInputBoundary interactor = new StudyModeInteractor(studymodeDataAccessInterface, failurePresenter);
        interactor.execute(inputData);
    }


    @Test
    void failureInvalidModuleTest() {
        // Create input data for selecting Module 1
        StudyModeInputData inputData = new StudyModeInputData("Module 0");

        // Set up Im-Memory Data Access and Output Boundary
        StudyModeDataAccessInterface studymodeDataAccessInterface = new InMemoryStudyModeDataAccessInterface();
        StudyModeOutputBoundary failurePresenter = new StudyModeOutputBoundary() {
            @Override
            public void prepareSuccessView(StudyModeOutputData response) {
                fail("Unexpected failure, success view was called when there is an invalid module.");
            }

            @Override
            public void prepareFailView(String error) {
                assertEquals("Invalid module selected, Please choose from the following (Module 1 to 6)",
                        error);
            }

            @Override
            public void switchToStudyModeBeginView() {
                // Not useful in this test
            }

            @Override
            public void switchToModeSelectionView() {
                // Not useful in this test
            }
        };

        // Create the <StudyModeInteractor> and execute the selection
        StudyModeInputBoundary interactor = new StudyModeInteractor(studymodeDataAccessInterface, failurePresenter);
        interactor.execute(inputData);
    }

    @Test
    void successBackButtonTest() {
        // randomly pick an module name here, since it does not take us to the next view, it does not matter
        StudyModeInputData inputData = new StudyModeInputData("Module 1");

        // Set up the In-Memory Data
        StudyModeDataAccessInterface studymodeDataAccessInterface = new InMemoryStudyModeDataAccessInterface();

        // Create a ViewManagerModel to track the current view
        ViewManagerModel viewManagerModel = new ViewManagerModel();

        // Set up a Success Presenter to check if the user is redirected to <ModeSelectionView>
        StudyModeOutputBoundary successPresenter = new StudyModeOutputBoundary() {
            @Override
            public void prepareSuccessView(StudyModeOutputData outputData) {
                // Not used in this test
            }

            @Override
            public void prepareFailView(String errorMessage) {
                // Not used in this test
            }

            @Override
            public void switchToStudyModeBeginView() {
                // Not used in this test
            }

            @Override
            public void switchToModeSelectionView() {
                viewManagerModel.setState("mode selection");
                viewManagerModel.firePropertyChanged();
            }
        };

        // Create and execute the study mode interactor
        StudyModeInputBoundary interactor = new StudyModeInteractor(studymodeDataAccessInterface, successPresenter);
        interactor.execute(inputData);

        // Test if the view is navigated successfully
        interactor.switchToModeSelectionView();
        assertEquals("mode selection", viewManagerModel.getState());
    }
}
