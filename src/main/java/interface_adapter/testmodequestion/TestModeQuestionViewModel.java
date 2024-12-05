package interface_adapter.testmodequestion;

import interface_adapter.ViewModel;

/**
 * View Model for mode selection use case.
 */
public class TestModeQuestionViewModel extends ViewModel<TestModeQuestionState> {

    public TestModeQuestionViewModel() {
        super("test mode question");
        setState(new TestModeQuestionState());
    }
}
