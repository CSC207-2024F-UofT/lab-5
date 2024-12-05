package use_case.studymode;

/**
 * Output Data for the Study Mode Use Case.
 */
public class StudyModeOutputData {
    private final String module;

    public StudyModeOutputData(String module) {
        this.module = module;
    }

    public String getModule() {
        return module;
    }
}
