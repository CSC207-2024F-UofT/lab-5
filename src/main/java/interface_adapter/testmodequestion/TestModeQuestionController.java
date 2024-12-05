package interface_adapter.testmodequestion;

import java.util.ArrayList;

import use_case.testmodequestion.TestModeQuestionInputBoundary;
import use_case.testmodequestion.TestModeQuestionInputData;

/**
 * The controller for test mode question use case.
 */
public class TestModeQuestionController {
    private static TestModeQuestionInputBoundary tmqInteractor;

    public TestModeQuestionController(TestModeQuestionInputBoundary testModeQuestionInteractor) {
        this.tmqInteractor = testModeQuestionInteractor;
    }

    /**
     * Execute the test mode question use case.
     * @param correctnumber the number of correct questions
     * @param wrongquestions the arraylist of wrong questions
     *
     */
    public void execute(int correctnumber, ArrayList<String> wrongquestions) {
        final TestModeQuestionInputData input = new TestModeQuestionInputData(correctnumber, wrongquestions);
        tmqInteractor.execute(input);
    }

    /**
     * Execute the "switch to Test Result View" use case.
     */
    public static void switchToTestResultView() {
        tmqInteractor.switchToTestResultView();
    }
}
