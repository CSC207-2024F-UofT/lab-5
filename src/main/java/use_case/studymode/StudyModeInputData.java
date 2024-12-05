package use_case.studymode;

/**
 * The Input Data for the Study Mode Use Case.
 */
public class StudyModeInputData {
    private final String module;

    public StudyModeInputData(String module) {
        this.module = module;
    }

    String getModule() {
        return module;
    }

}
