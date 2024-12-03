package use_case.survey1;

import entity.User;

/**
 * The output boundary for the Survey1 Use Case.
 */
public interface Survey1OutputBoundary {

    /**
     * Switches to the SurveySecondPage View.
     * @param currentUser the current user
     */
    void switchToSurveySecondPageView(User currentUser);

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
