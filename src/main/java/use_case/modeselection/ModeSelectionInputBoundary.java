package use_case.modeselection;

/**
 * Input Boundary for actions which are related to mode selection.
 */
public interface ModeSelectionInputBoundary {

    /**
     * Executes the modeselection use case.
     * @param modeselectionInputData the input data
     */
    void execute(ModeSelectionInputData modeselectionInputData);

    /**
     * Switch to Study Mode View.
     */
    void switchToStudyModeView();

    /**
     * Switch to Test Mode View.
     */
    void switchToTestModeView();
}
