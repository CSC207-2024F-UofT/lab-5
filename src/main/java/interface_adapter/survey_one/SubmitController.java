package interface_adapter.survey_one;

import java.util.List;

import use_case.survey_one.Survey1InputBoundary;
import use_case.survey_one.Survey1InputData;

/**
 * The controller for the Login Use Case.
 */
public class SubmitController {
    private final Survey1InputBoundary survey1UseCaseInteractor;

    public SubmitController(Survey1InputBoundary survey1UseCaseInteractor) {

        this.survey1UseCaseInteractor = survey1UseCaseInteractor;
    }

    /**
     * Executes the "submit survey" Use Case.
     * @param selectedGenres the genres selected by the user
     * @param username username of the currently logged-in user
     */
    public void execute(List<String> selectedGenres, String username) {
        final Survey1InputData survey1InputData = new Survey1InputData(selectedGenres);
        this.survey1UseCaseInteractor.execute(survey1InputData, username);
    }
}
