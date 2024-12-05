package interface_adapter.testresult;

import interface_adapter.ViewManagerModel;
import interface_adapter.change_password.LoggedInViewModel;
import use_case.testresult.TestresultOutputBoundary;
import use_case.testresult.TestresultOutputData;

/**
 * Presenter for Test result use case.
 */
public class TestresultPresenter implements TestresultOutputBoundary {
    private final TestresultViewModel testresultViewModel;
    private final LoggedInViewModel loggedInViewModel;
    private final ViewManagerModel viewManagerModel;

    public TestresultPresenter(TestresultViewModel testResultViewModel, LoggedInViewModel loggedInViewModel,
                               ViewManagerModel viewManagerModel) {
        this.testresultViewModel = testResultViewModel;
        this.loggedInViewModel = loggedInViewModel;
        this.viewManagerModel = viewManagerModel;
    }

    @Override
    public void prepareSuccessView(TestresultOutputData outputData) {
        testresultViewModel.setCorrectQuestions(outputData.getCorrectQuestions());
        testresultViewModel.setIncorrectQuestions(outputData.getIncorrectQuestions());
    }

    @Override
    public void prepareFailView(String errorMessage) {
        final TestresultState testresultState = new TestresultState();
        testresultState.setTestResultError(errorMessage);
        testresultViewModel.firePropertyChanged();
    }

    @Override
    public void switchToLoggedInView() {
        viewManagerModel.setState(loggedInViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

}
