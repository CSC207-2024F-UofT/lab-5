package interface_adapter.studymodebegin;

import use_case.studymodebegin.StudyModeBeginInputBoundary;
import use_case.studymodebegin.StudyModeBeginInputData;

/**
 * The controller for the Study Mode Use Case.
 */
public class StudyModeBeginController {

    private final StudyModeBeginInputBoundary studyModeBeginInputBoundary;

    public StudyModeBeginController(StudyModeBeginInputBoundary studyModeBeginInputBoundary) {
        this.studyModeBeginInputBoundary = studyModeBeginInputBoundary;
    }

    /**
     * Executes the Study Mode Begin Use Case.
     * @param module the module that the user selected
     */
    public void execute(String module) {
        final StudyModeBeginInputData studyModeBeginInputData = new StudyModeBeginInputData(module);

        studyModeBeginInputBoundary.execute(studyModeBeginInputData);
    }

    /**
     * Executes the "switch to StudyModeQuestion" Use Case.
     */
    public void switchToStudyModeQuestionView() {
        studyModeBeginInputBoundary.switchToStudyModeQuestionView();
    }

    /**
     * Executes the "switch to StudyMode" Use Case.
     */
    public void switchToStudyModeView() {
        studyModeBeginInputBoundary.switchToStudyModeView();
    }
}
