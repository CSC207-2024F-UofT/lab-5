package use_case.studymodebegin;

/**
 * Output Data for the Study Mode Begin Use Case.
 */
public class StudyModeBeginOutputData {
    private final String module;

    public StudyModeBeginOutputData(String module) {
        this.module = module;
    }

    public String getModule() {
        return module;
    }
}
