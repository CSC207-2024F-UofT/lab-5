package use_case.studymodebegin;

/**
 * The output boundary for the Study Mode Begin Use Case.
 */
public interface StudyModeBeginOutputBoundary {
    /**
     * Prepares the success view for the Study Mode Use Case.
     * @param outputData the output data
     */
    void prepareSuccessView(StudyModeBeginOutputData outputData);

    /**
     * Prepares the failure view for the Study Mode Use Case.
     * @param errorMessage the explanation of the failure
     */
    void prepareFailView(String errorMessage);

    /**
     * Switch to the Study Mode Question View.
     */
    void switchToStudyModeQuestionView();

    /**
     * Switch to the Study Mode View.
     */
    void switchToStudyModeView();
}
