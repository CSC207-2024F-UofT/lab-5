package use_case.survey_one;

/**
 * The output boundary for the Survey1 Use Case.
 */
public interface Survey1OutputBoundary {

    /**
     * Prepares the failure view for the Login Use Case.
     * @param error the explanation of the failure
     */
    void prepareFailView(String error);

    /**
     * Prepares the success view for the Login Use Case.
     * @param survey1OutputData the output data
     */
    void prepareSuccessView(Survey1OutputData survey1OutputData);
}
