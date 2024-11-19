package use_case.survey1;

/**
 * Input Boundary for actions which are related to first step of survey.
 */
public interface Survey1InputBoundary {

    /**
     * Executes the Survey1 use case.
     * @param survey1InputData the input data
     */
    void execute(Survey1InputData survey1InputData);

    /**
     * Executes the switch to SurveySecondPageView use case.
     */
    void switchToSurveySecondPageView();
}
