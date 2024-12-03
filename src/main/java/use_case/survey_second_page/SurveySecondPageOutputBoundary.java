package use_case.survey_second_page;

/**
 * The output boundary for the Login Use Case.
 */
public interface SurveySecondPageOutputBoundary {
    /**
     * Prepares the success view for the Survey Use Case.
     * @param outputData the output data
     */
    void prepareSuccessView(SurveySecondPageOutputData outputData);

    /**
     * Prepares the failure view for the Survey Use Case.
     * @param errorMessage the explanation of the failure
     */
    void prepareFailView(String errorMessage);

    /**
     * Switches to the Home View.
     */
    void switchToHomeView();
}
