package use_case.modeselection;

/**
 * The output boundary for the Mode Selection Use Case.
 */
public interface ModeSelectionOutputBoundary {
    /**
     * Prepares the success view for the mode selection Use Case.
     *
     * @param outputData the output data
     */
    void prepareSuccessView(ModeSelectionOutputData outputData);

    /**
     * Prepares the failure view for the Signup Use Case.
     *
     * @param errorMessage the explanation of the failure
     */
    void prepareFailView(String errorMessage);

    /**
     * Switches to the Study mode View.
     */
    void switchToStudyModeView();

    /**
     * Switches to the Test mode View.
     */
    void switchToTestModeView();

}
