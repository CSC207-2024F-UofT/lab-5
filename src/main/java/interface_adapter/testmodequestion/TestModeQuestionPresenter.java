package interface_adapter.testmodequestion;

import interface_adapter.ViewManagerModel;
import interface_adapter.testresult.TestresultState;
import interface_adapter.testresult.TestresultViewModel;
import use_case.testmodequestion.TestModeQuestionOutputBoundary;
import use_case.testmodequestion.TestModeQuestionOutputData;

/**
 * Presenter for Test Mode Question use case.
 */
public class TestModeQuestionPresenter implements TestModeQuestionOutputBoundary {
    private TestModeQuestionViewModel testModeQuestionViewModel;
    private ViewManagerModel viewManagerModel;
    private TestresultViewModel testresultViewModel;

    public TestModeQuestionPresenter(ViewManagerModel viewManagerModel,
                                     TestModeQuestionViewModel testModeQuestionViewModel,
                                     TestresultViewModel testresultViewModel) {
        this.testModeQuestionViewModel = testModeQuestionViewModel;
        this.viewManagerModel = viewManagerModel;
        this.testresultViewModel = testresultViewModel;
    }

    @Override
    public void prepareSuccessView(TestModeQuestionOutputData output) {
        final TestresultState testresultState = testresultViewModel.getState();
        testresultState.setCorrectQuestions(output.getCorrectQuestions());
        testresultState.setIncorrectQuestions(output.getIncorrectQuestions());
        testresultViewModel.setState(testresultState);

        testresultViewModel.firePropertyChanged();

        viewManagerModel.setState(testresultViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

    @Override
    public void prepareFailView(String errorMessage) {
        // assume there is no failure case when selecting a mode.
    }

    @Override
    public void switchToTestResultView() {
        viewManagerModel.setState(testresultViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }
}

