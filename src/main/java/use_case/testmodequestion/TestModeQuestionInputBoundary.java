package use_case.testmodequestion;

/**
 * Input Boundary for actions which are related to study mode question.
 */
public interface TestModeQuestionInputBoundary {

    /**
     * Executes the TestModeQuestion use case.
     * @param testModeQuestionInputData the input data
     */
    void execute(TestModeQuestionInputData testModeQuestionInputData);

    /**
     * Switch to the TestResult(?) View.
     */
    void switchToTestResultView();
}

