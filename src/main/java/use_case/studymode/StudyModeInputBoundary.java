package use_case.studymode;

/**
 * Input Boundary for actions which are related to study mode.
 */
public interface StudyModeInputBoundary {

    /**
     * Executes the study mode use case.
     * @param studyModeInputData the input data
     */
    void execute(StudyModeInputData studyModeInputData);

    /**
     * Switch to the Study Mode Begin View.
     */
    void switchToStudyModeBeginView();

    /**
     * Switch to the Mode Selection View.
     */
    void switchToModeSelectionView();
}
