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

        if (firstMovie.isEmpty() || secondMovie.isEmpty() || thirdMovie.isEmpty()) {
            surveySecondPagePresenter.prepareFailView("nonExistentMovie: Movie not found.");
        }
        else {

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
