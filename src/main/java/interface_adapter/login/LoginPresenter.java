package interface_adapter.login;

import interface_adapter.ForgotPassword.ForgotPasswordViewModel;
import interface_adapter.ViewManagerModel;
import interface_adapter.profile.ProfileState;
import interface_adapter.profile.ProfileViewModel;
import use_case.login.LoginOutputBoundary;
import use_case.login.LoginOutputData;

/**
 * The Presenter for the Login Use Case.
 */
public class LoginPresenter implements LoginOutputBoundary {

    private final LoginViewModel loginViewModel;
    private final ProfileViewModel profileViewModel;
    private final ViewManagerModel viewManagerModel;
    private final ForgotPasswordViewModel forgotPasswordViewModel;

    public LoginPresenter(ViewManagerModel viewManagerModel,
                          ProfileViewModel profileViewModel,
                          LoginViewModel loginViewModel,
                          ForgotPasswordViewModel forgotPasswordViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.profileViewModel = profileViewModel;
        this.loginViewModel = loginViewModel;
        this.forgotPasswordViewModel = forgotPasswordViewModel;
    }

    @Override
    public void prepareSuccessView(LoginOutputData response) {
        // On success, switch to the logged in view.

        final ProfileState profileState = profileViewModel.getState();
        profileState.setUsername(response.getUsername());
        this.profileViewModel.setState(profileState);
        this.profileViewModel.firePropertyChanged();

        this.viewManagerModel.setState(profileViewModel.getViewName());
        this.viewManagerModel.firePropertyChanged();
    }

    @Override
    public void prepareFailView(String error) {
        final LoginState loginState = loginViewModel.getState();
        loginState.setLoginError(error);
        loginViewModel.firePropertyChanged();
    }

    public void switchToForgotPasswordView() {
        viewManagerModel.setState(forgotPasswordViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }
}
