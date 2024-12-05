package use_case.studymode;

/**
 * The output boundary for the Study Mode Use Case.
 */
public interface StudyModeOutputBoundary {
    /**
     * Prepares the success view for the Study Mode Use Case.
     * @param outputData the output data
     */
    void prepareSuccessView(StudyModeOutputData outputData);

    /**
     * Prepares the failure view for the Study Mode Use Case.
     * @param errorMessage the explanation of the failure
     */
    void prepareFailView(String errorMessage);

    /**
     * Switch to the Study Mode Begin View.
     */
    void switchToStudyModeBeginView();

    /**
     * Switch to the LoggedIn View.
     */
    void switchToModeSelectionView();
}
