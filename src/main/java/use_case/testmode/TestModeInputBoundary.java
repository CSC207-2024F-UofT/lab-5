package use_case.testmode;

/**
 * Input Boundary for actions which are related to test mode.
 */
public interface TestModeInputBoundary {
    /**
     * Executes the test mode use case.
     * @param testModeInputData the input data
     */
    void execute(TestModeInputData testModeInputData);

    /**
     * Switch to the Test Mode View.
     */
    void switchToTestModeQuestionView();

    /**
     * Switch to the Mode Selection View.
     */
    void switchToModeSelectionView();
}
