package use_case.login;

import entity.User;

/**
 * The Login Interactor.
 */
public class LoginInteractor implements LoginInputBoundary {
    private final LoginUserDataAccessInterface userDataAccessObject;
    private final LoginOutputBoundary loginPresenter;
    private User loggedInUser;

    public LoginInteractor(LoginUserDataAccessInterface userDataAccessInterface,
                           LoginOutputBoundary loginOutputBoundary) {
        this.userDataAccessObject = userDataAccessInterface;
        this.loginPresenter = loginOutputBoundary;
    }

    @Override
    public void execute(LoginInputData loginInputData) {
        final String username = loginInputData.getUsername();
        final String password = loginInputData.getPassword();
        if (!userDataAccessObject.existsByName(username)) {
            loginPresenter.prepareFailView(username + ": Account does not exist.");
        }
        else {
            final String pwd = userDataAccessObject.get(username).getPassword();
            if (!password.equals(pwd)) {
                loginPresenter.prepareFailView("Incorrect password for \"" + username + "\".");
            }
            else {

                final User loggedInUser = userDataAccessObject.get(loginInputData.getUsername());

                userDataAccessObject.setCurrentUsername(loggedInUser.getName());
                final LoginOutputData loginOutputData = new LoginOutputData(loggedInUser.getName(), false);
                loginPresenter.prepareSuccessView(loginOutputData);
            }
        }
    }

    public User getLoggedInUser(){ return this.loggedInUser; }
    /**
     * Executes the switch to signup view use case.
     */
    @Override
    public void switchToSignUpView() {
        loginPresenter.switchToSignUpView();
    }
}
