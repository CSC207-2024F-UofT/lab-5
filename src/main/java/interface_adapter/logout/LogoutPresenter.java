package interface_adapter.logout;

import interface_adapter.ViewManagerModel;
import interface_adapter.change_password.LoggedInViewModel;
import interface_adapter.login.LoginState;
import interface_adapter.login.LoginViewModel;
import use_case.logout.LogoutOutputBoundary;
import use_case.logout.LogoutOutputData;

/**
 * The Presenter for the Logout Use Case.
 */
public class LogoutPresenter implements LogoutOutputBoundary {

    private LoggedInViewModel loggedInViewModel;
    private ViewManagerModel viewManagerModel;
    private LoginViewModel loginViewModel;

    public LogoutPresenter(ViewManagerModel viewManagerModel,
                          LoggedInViewModel loggedInViewModel,
                           LoginViewModel loginViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.loginViewModel = loginViewModel;
        this.loggedInViewModel = loggedInViewModel;
    }

    @Override
    public void prepareSuccessView(LogoutOutputData response) {
        // We need to switch to the login view, which should have
        // an empty username and password.

        // We also need to set the username in the LoggedInState to
        // the empty string

        // 1. get the LoggedInState out of the appropriate View Model,
        final LoginState logoutState = loginViewModel.getState();
        // 2. set the username in the state to the empty string
        logoutState.setUsername("");
        // 3. set the state in the LoggedInViewModel to the updated state
        this.loginViewModel.setState(logoutState);
        // 4. firePropertyChanged so that the View that is listening is updated.
        loginViewModel.firePropertyChanged();

        // TODO: have prepareSuccessView update the LoginState
        // 5. get the LoginState out of the appropriate View Model,
        // 6. set the username and password in the state to the empty string
        // 7. set the state in the LoginViewModel to the updated state
        // 8. firePropertyChanged so that the View that is listening is updated.

        // This code tells the View Manager to switch to the LoginView.
        this.viewManagerModel.setState(loginViewModel.getViewName());
        this.viewManagerModel.firePropertyChanged();
    }

    @Override
    public void prepareFailView(String error) {
        // No need to add code here. We'll assume that logout can't fail.
        // Thought question: is this a reasonable assumption?
    }
}
