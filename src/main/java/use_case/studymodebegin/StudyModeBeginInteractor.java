package use_case.studymodebegin;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * The Study Mode Begin Interactor.
 */
public class StudyModeBeginInteractor implements StudyModeBeginInputBoundary {
    private final StudyModeBeginDataAccessInterface studyModeBeginDataAccessInterface;
    private final StudyModeBeginOutputBoundary studyModeBeginOutputBoundary;

    public StudyModeBeginInteractor(StudyModeBeginDataAccessInterface studyModeBeginDataAccessInterface,
                                    StudyModeBeginOutputBoundary studyModeBeginOutputBoundary) {
        this.studyModeBeginDataAccessInterface = studyModeBeginDataAccessInterface;
        this.studyModeBeginOutputBoundary = studyModeBeginOutputBoundary;
    }

    @Override
    public void execute(StudyModeBeginInputData studyModeBeginInputData) {
        final String module = studyModeBeginInputData.getModule();

        if (!isValidModule(module)) {
            studyModeBeginOutputBoundary.prepareFailView("Invalid module, we only have Module 1 to 6.");
        }
        else {
            studyModeBeginDataAccessInterface.setModule(module);
            final StudyModeBeginOutputData outputData = new StudyModeBeginOutputData(module);
            studyModeBeginOutputBoundary.prepareSuccessView(outputData);
        }
    }

    private boolean isValidModule(String module) {
        final Set<String> validModules = new HashSet<>(Arrays.asList("Module 1", "Module 2", "Module 3",
                "Module 4", "Module 5", "Module 6"));
        return validModules.contains(module);
    }

    @Override
    public void switchToStudyModeQuestionView() {
        studyModeBeginOutputBoundary.switchToStudyModeQuestionView();
    }

    @Override
    public void switchToStudyModeView() {
        studyModeBeginOutputBoundary.switchToStudyModeView();
    }
}
