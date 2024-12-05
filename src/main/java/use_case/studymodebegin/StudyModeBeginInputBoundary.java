package use_case.studymodebegin;

/**
 * Input Boundary for actions which are related to study mode begin.
 */
public interface StudyModeBeginInputBoundary {
    /**
     * Executes the study mode use case.
     * @param studyModeBeginInputData the input data
     */
    void execute(StudyModeBeginInputData studyModeBeginInputData);

    /**
     * Switch to the Study Mode Question View.
     */
    void switchToStudyModeQuestionView();

    /**
     * Switch to the Study Mode View.
     */
    void switchToStudyModeView();
}
