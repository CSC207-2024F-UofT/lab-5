package interface_adapter.survey_second_page;

import use_case.survey_second_page.SurveySecondPageInputBoundary;
import use_case.survey_second_page.SurveySecondPageInputData;

/**
 * The controller for the Survey Use Case.
 */
public class SurveySecondPageController {

    private final SurveySecondPageInputBoundary surveySecondPageInteractor;

    public SurveySecondPageController(SurveySecondPageInputBoundary surveySecondPageInteractor) {
        this.surveySecondPageInteractor = surveySecondPageInteractor;
    }

    /**
     * Executes the Survey Use Case.
     * @param firstMovie first
     * @param secondMovie second
     * @param thirdMovie third
     */
    public void execute(String firstMovie, String secondMovie, String thirdMovie) {
        final SurveySecondPageInputData surveySecondPageInputData =
                new SurveySecondPageInputData(firstMovie, secondMovie, thirdMovie);

        surveySecondPageInteractor.execute(surveySecondPageInputData);
    }

    /**
     * Executes the "switch to HomeView" Use Case.
     */
    public void switchToHomeView() {
        surveySecondPageInteractor.switchToHomeView();
    }
}
