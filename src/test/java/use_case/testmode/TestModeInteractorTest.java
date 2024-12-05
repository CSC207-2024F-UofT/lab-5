package use_case.testmode;

import interface_adapter.ViewManagerModel;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TestModeInteractorTest {

    @Test
    void successBeginTest() {
        // Create a ViewManagerModel to track the current view
        ViewManagerModel viewManagerModel = new ViewManagerModel();

        // Set up a Success Presenter to check if the user is redirected to <TestModeQuestionView>
        TestModeOutputBoundary successPresenter = new TestModeOutputBoundary() {
            @Override
            public void prepareSuccessView() {
                // Not useful in this test
            }

            @Override
            public void prepareFailView(String errorMessage) {
                fail("Unexpected failure while selecting module");
            }

            @Override
            public void switchToTestModeQuestionView() {
                viewManagerModel.setState("test mode question");
                viewManagerModel.firePropertyChanged();
            }

            @Override
            public void switchToModeSelectionView() {
                // Not useful in this test
            }
        };

        // Create and execute the <TestMode> Interactor
        TestModeInputBoundary interactor = new TestModeInteractor(successPresenter);
        interactor.execute();

        // Test if the view is navigated successfully
        interactor.switchToTestModeQuestionView();
        assertEquals("test mode question", viewManagerModel.getState());
    }

    @Test
    void successBackButtonTest() {
        // Create a ViewManagerModel to track the current view
        ViewManagerModel viewManagerModel = new ViewManagerModel();

        // Set up a Success Presenter to check if the user is redirected to <ModeSelectionView>
        TestModeOutputBoundary successPresenter = new TestModeOutputBoundary() {
            @Override
            public void prepareSuccessView() {
                // Not useful in this test
            }

            @Override
            public void prepareFailView(String errorMessage) {
                fail("Unexpected failure while selecting module");
            }

            @Override
            public void switchToTestModeQuestionView() {
                // Not useful in this test
            }

            @Override
            public void switchToModeSelectionView() {
                viewManagerModel.setState("mode selection");
                viewManagerModel.firePropertyChanged();
            }
        };

        // Create and execute the <TestMode> Interactor
        TestModeInputBoundary interactor = new TestModeInteractor(successPresenter);
        interactor.execute();

        // Test if the view is navigated successfully
        interactor.switchToModeSelectionView();
        assertEquals("mode selection", viewManagerModel.getState());
    }
}
