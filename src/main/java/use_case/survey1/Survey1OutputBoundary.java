package use_case.survey1;

/**
 * The output boundary for the Survey1 Use Case.
 */
public interface Survey1OutputBoundary {

    /**
     * Switches to the SurveySecondPage View.
     */
    void switchToSurveySecondPageView();

    /**
     * Prepares the failure view for the Login Use Case.
     * @param errorMessage the explanation of the failure
     */
    void prepareFailView(String errorMessage);

    /**
     * Prepares the success view for the Login Use Case.
     * @param survey1OutputData the output data
     */
    void prepareSuccessView(Survey1OutputData survey1OutputData);
}
