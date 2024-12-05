package data_access;

import use_case.studymode.StudyModeDataAccessInterface;

/**
 * In-memory implementation of the DAO for topic chosen in Study Mode. This implementation does
 * NOT persist data between runs of the program.
 */
public class InMemoryStudyModeDataAccessInterface implements StudyModeDataAccessInterface {

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
