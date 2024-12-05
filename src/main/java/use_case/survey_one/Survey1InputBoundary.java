package use_case.survey_one;

/**
 * Input Boundary for actions which are related to first step of survey.
 */
public interface Survey1InputBoundary {

    /**
     * Executes the Survey1 use case.
     * @param survey1InputData the input data
     * @param username the user who is taking the survey
     */
    void execute(Survey1InputData survey1InputData, String username);
}
