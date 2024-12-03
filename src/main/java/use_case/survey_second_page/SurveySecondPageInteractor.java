package use_case.survey_second_page;

import entity.UserFactory;

/**
 * The Survey Second Page Interactor.
 */
public class SurveySecondPageInteractor implements SurveySecondPageInputBoundary {
    private final SurveySecondPageDataAccessInterface userDataAccessObject;
    private final SurveySecondPageOutputBoundary surveySecondPagePresenter;
    private final UserFactory userFactory;

    public SurveySecondPageInteractor(SurveySecondPageDataAccessInterface surveySecondPageDataAccessInterface,
                                      SurveySecondPageOutputBoundary surveySecondPagePresenter,
                                      UserFactory userFactory) {
        this.userDataAccessObject = surveySecondPageDataAccessInterface;
        this.surveySecondPagePresenter = surveySecondPagePresenter;
        this.userFactory = userFactory;
    }

    @Override
    public void execute(SurveySecondPageInputData surveySecondPageInputData) {
        final String firstMovie = surveySecondPageInputData.getFirstMovie();
        final String secondMovie = surveySecondPageInputData.getSecondMovie();
        final String thirdMovie = surveySecondPageInputData.getThirdMovie();

        if (!userDataAccessObject.movieExists(firstMovie) || !userDataAccessObject.movieExists(secondMovie)
                || !userDataAccessObject.movieExists(thirdMovie)) {

            if (!userDataAccessObject.movieExists(firstMovie)) {
                surveySecondPagePresenter.prepareFailView(firstMovie + ": Movie not found.");
            }
            if (!userDataAccessObject.movieExists(secondMovie)) {
                surveySecondPagePresenter.prepareFailView(secondMovie + ": Movie not found.");
            }
            if (!userDataAccessObject.movieExists(thirdMovie)) {
                surveySecondPagePresenter.prepareFailView(thirdMovie + ": Movie not found.");
            }
        }
        else {
            // TODO: implement add to playlist
            final SurveySecondPageOutputData surveySecondPageOutputData = new SurveySecondPageOutputData(firstMovie,
                    secondMovie, thirdMovie, false);
            surveySecondPagePresenter.prepareSuccessView(surveySecondPageOutputData);
        }
    }

    /**
     * Executes the switch to signup view use case.
     */
    @Override
    public void switchToHomeView() {
        surveySecondPagePresenter.switchToHomeView();
    }
}
