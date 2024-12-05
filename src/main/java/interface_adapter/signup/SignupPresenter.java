package interface_adapter.signup;

import interface_adapter.ViewManagerModel;
import interface_adapter.home.HomeViewModel;
import interface_adapter.home.LoggedInState;
import interface_adapter.login.LoginViewModel;
import interface_adapter.survey_one.Survey1ViewModel;
import use_case.signup.SignupOutputBoundary;
import use_case.signup.SignupOutputData;

/**
 * The Presenter for the Signup Use Case.
 */
public class SignupPresenter implements SignupOutputBoundary {

    private final SignupViewModel signupViewModel;
    private final HomeViewModel homeViewModel;
    private final ViewManagerModel viewManagerModel;
    private final LoginViewModel loginViewModel;
    private final Survey1ViewModel survey1ViewModel;

    public SignupPresenter(ViewManagerModel viewManagerModel,
                           SignupViewModel signupViewModel,
                           LoginViewModel loginViewModel,
                           HomeViewModel homeViewModel,
                           Survey1ViewModel survey1ViewModel) {

        this.viewManagerModel = viewManagerModel;
        this.signupViewModel = signupViewModel;
        this.homeViewModel = homeViewModel;
        this.loginViewModel = loginViewModel;
        this.survey1ViewModel = survey1ViewModel;
    }

    @Override
    public void prepareSuccessView(SignupOutputData response) {
        // On success, switch to the login view.
        final LoggedInState loggedinState = homeViewModel.getState();
        loggedinState.setUsername(response.getUsername());
        this.homeViewModel.setState(loggedinState);
        homeViewModel.firePropertyChanged();

        viewManagerModel.setState(homeViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

    @Override
    public void prepareFailView(String error) {
        final SignupState signupState = signupViewModel.getState();
        signupState.setUsernameError(error);
        signupViewModel.firePropertyChanged();
    }

    @Override
    public void switchToSurvey1View(String uname) {
        survey1ViewModel.getState().setUsername(uname);
        viewManagerModel.setState(survey1ViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

    @Override
    public void switchToLoginView() {
        viewManagerModel.setState(loginViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }
}
