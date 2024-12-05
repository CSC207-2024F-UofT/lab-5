package use_case.testmodequestion;

/**
 * The output boundary for the Test Mode Question Use Case.
 */
public interface TestModeQuestionOutputBoundary {
    /**
     * Prepares the success view for the Test Mode Question Use Case.
     * @param outputData the output data
     */
    void prepareSuccessView(TestModeQuestionOutputData outputData);

    /**
     * Prepares the failure view for the Test Mode Question Use Case.
     * @param errorMessage the explanation of the failure
     */
    void prepareFailView(String errorMessage);

    /**
     * Switch to the Test Result View.
     */
    void switchToTestResultView();
}
