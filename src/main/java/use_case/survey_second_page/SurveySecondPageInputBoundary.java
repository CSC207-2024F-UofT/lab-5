package use_case.survey_second_page;

/**
 * Input Boundary for actions which are related to taking the survey.
 */
public interface SurveySecondPageInputBoundary {

    /**
     * Executes the login use case.
     * @param surveySecondPageInputData the input data
     */
    void execute(SurveySecondPageInputData surveySecondPageInputData);

    /**
     * Executes the switch to home view use case.
     */
    void switchToHomeView();

}
