package osiris.use_case.login;

import osiris.entity.User;

/**
 * The Login Interactor.
 */
public class LoginInteractor implements LoginInputBoundary {
    private final LoginUserDataAccessInterface userDataAccessObject;
    private final LoginOutputBoundary loginPresenter;

    public LoginInteractor(LoginUserDataAccessInterface userDataAccessInterface,
                           LoginOutputBoundary loginOutputBoundary) {
        this.userDataAccessObject = userDataAccessInterface;
        this.loginPresenter = loginOutputBoundary;
    }

    @Override
    public void execute(LoginInputData loginInputData) {
        final String username = loginInputData.getEmail();
        final String password = loginInputData.getPassword();
        final String access_code = userDataAccessObject.get(username).getAccessCode();
        if (!userDataAccessObject.existsByName(username)) {
            loginPresenter.prepareFailView(username + ": Account does not exist.");
        }
        else {
            final String pwd = userDataAccessObject.get(username).getPassword();
            if (!password.equals(pwd)) {
                loginPresenter.prepareFailView("Incorrect password for \"" + username + "\".");
            }
            else {

                final User user = userDataAccessObject.get(loginInputData.getEmail());

                userDataAccessObject.setCurrentEmail(user.getEmail());
                final LoginOutputData loginOutputData = new LoginOutputData(user.getEmail(), false);
                loginPresenter.prepareSuccessView(loginOutputData);
            }
        }
    }

    @Override
    public void switchToWelcomeView() {
        loginPresenter.switchToWelcomeView();
    }
}
