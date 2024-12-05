package use_case.studymode;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * The Study Mode Interactor.
 */
public class StudyModeInteractor implements StudyModeInputBoundary {
    private final StudyModeDataAccessInterface studyModeDataAccessInterface;
    private final StudyModeOutputBoundary studyModeOutputBoundary;

    public StudyModeInteractor(StudyModeDataAccessInterface studyModeDataAccessInterface,
                               StudyModeOutputBoundary studyModeOutputBoundary) {
        this.studyModeDataAccessInterface = studyModeDataAccessInterface;
        this.studyModeOutputBoundary = studyModeOutputBoundary;
    }

    @Override
    public void execute(StudyModeInputData studyModeInputData) {
        final String module = studyModeInputData.getModule();

        if (!isValidModule(module)) {
            studyModeOutputBoundary.prepareFailView("Invalid module selected, Please choose from the "
                    + "following (Module 1 to 6)");
        }
        else {
            studyModeDataAccessInterface.setModule(module);
            final StudyModeOutputData outputData = new StudyModeOutputData(module);
            studyModeOutputBoundary.prepareSuccessView(outputData);
        }
    }

    private boolean isValidModule(String module) {
        final Set<String> validModules = new HashSet<>(Arrays.asList("Module 1", "Module 2", "Module 3",
                "Module 4", "Module 5", "Module 6"));
        return validModules.contains(module);
    }

    @Override
    public void switchToStudyModeBeginView() {
        studyModeOutputBoundary.switchToStudyModeBeginView();
    }

    @Override
    public void switchToModeSelectionView() {
        studyModeOutputBoundary.switchToModeSelectionView();
    }
}
