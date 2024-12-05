package use_case.studymodequestion;

/**
 * The output boundary for the Study Mode Question Use Case.
 */
public interface StudyModeQuestionOutputBoundary {
    /**
     * Prepares the success view for the Study Mode Question Use Case.
     * @param outputData the output data
     */
    void prepareSuccessView(StudyModeQuestionOutputData outputData);

    /**
     * Prepares the failure view for the Study Mode Question Use Case.
     * @param errorMessage the explanation of the failure
     */
    void prepareFailView(String errorMessage);

    /**
     * Switch back to study mode.
     */
    void switchToStudyMode();
}
