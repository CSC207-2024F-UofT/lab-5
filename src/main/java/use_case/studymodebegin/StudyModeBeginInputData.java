package use_case.studymodebegin;

/**
 * The Input Data for the Study Mode Begin Use Case.
 */
public class StudyModeBeginInputData {
    private final String module;

    public StudyModeBeginInputData(String module) {
        this.module = module;
    }

    String getModule() {
        return module;
    }
}
