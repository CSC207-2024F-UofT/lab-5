package interface_adapter.survey_second_page;

import interface_adapter.ViewManagerModel;
import interface_adapter.home.HomeViewModel;
import interface_adapter.home.LoggedInState;
import use_case.survey_second_page.SurveySecondPageOutputBoundary;
import use_case.survey_second_page.SurveySecondPageOutputData;

/**
 * Presenter for Second Survey Page.
 */
public class SurveySecondPagePresenter implements SurveySecondPageOutputBoundary {
    private final SurveySecondPageViewModel surveySecondPageViewModel;
    private final HomeViewModel homeViewModel;
    private final ViewManagerModel viewManagerModel;

    public SurveySecondPagePresenter(ViewManagerModel viewManagerModel,
                                     SurveySecondPageViewModel surveySecondPageViewModel,
                                     HomeViewModel homeViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.surveySecondPageViewModel = surveySecondPageViewModel;
        this.homeViewModel = homeViewModel;
    }

    @Override
    public void prepareSuccessView(SurveySecondPageOutputData response) {
        // On success, switch to the home view.
        final LoggedInState loggedInState = homeViewModel.getState();
        this.homeViewModel.setState(loggedInState);
        homeViewModel.firePropertyChanged();

        viewManagerModel.setState(homeViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

    @Override
    public void prepareFailView(String error) {
        final SurveySecondPageState surveySecondPageState = surveySecondPageViewModel.getState();
        surveySecondPageState.setMovieEntryError(error);
        surveySecondPageViewModel.firePropertyChanged();
    }

    /**
     * Switch to Home View.
     */
    @Override
    public void switchToHomeView() {
        viewManagerModel.setState(homeViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }
}
