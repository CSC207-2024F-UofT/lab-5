package data_access;

import use_case.studymodebegin.StudyModeBeginDataAccessInterface;

/**
 * In-memory implementation of the DAO for topic chosen in Study Mode. This implementation does
 * NOT persist data between runs of the program.
 */
public class InMemoryStudyModeBeginDataAccessInterface implements StudyModeBeginDataAccessInterface {

    private String module;

    @Override
    public String getModule() {
        return this.module;
    }

    @Override
    public void setModule(String module) {
        this.module = module;
    }
}
