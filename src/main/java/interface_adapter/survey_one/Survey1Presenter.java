package interface_adapter.survey_one;

import interface_adapter.ViewManagerModel;
import interface_adapter.survey_second_page.SurveySecondPageState;
import interface_adapter.survey_second_page.SurveySecondPageViewModel;
import use_case.survey_one.Survey1OutputBoundary;
import use_case.survey_one.Survey1OutputData;

/**
 * Presenter for Second Survey Page.
 */
public class Survey1Presenter implements Survey1OutputBoundary {

    private final Survey1ViewModel survey1ViewModel;
    private final SurveySecondPageViewModel surveySecondPageViewModel;
    private final ViewManagerModel viewManagerModel;

    public Survey1Presenter(Survey1ViewModel survey1ViewModel,
                            SurveySecondPageViewModel surveySecondPageViewModel,
                            ViewManagerModel viewManagerModel) {
        this.survey1ViewModel = survey1ViewModel;
        this.surveySecondPageViewModel = surveySecondPageViewModel;
        this.viewManagerModel = viewManagerModel;
    }

    @Override
    public void prepareFailView(String error) {
        final Survey1State survey1State = survey1ViewModel.getState();
        survey1State.setSurvey1Error(error);
        survey1ViewModel.firePropertyChanged();
    }

    @Override
    public void prepareSuccessView(Survey1OutputData survey1OutputData) {
        final SurveySecondPageState surveySecondPageState = surveySecondPageViewModel.getState();
        this.surveySecondPageViewModel.setState(surveySecondPageState);
        surveySecondPageViewModel.firePropertyChanged();

        viewManagerModel.setState(surveySecondPageViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }
}
