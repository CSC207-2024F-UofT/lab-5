package osiris.use_case.login;

import osiris.entity.User;
import osiris.entity.UserFactory;
import osiris.interface_adapter.plaid.PlaidController;
import osiris.use_case.plaid.PlaidDataBaseUserAccessObjectInterface;
import osiris.use_case.plaid.PlaidInputBoundary;
import osiris.use_case.plaid.PlaidInteractor;
import osiris.use_case.plaid.UserPlaidDataAccessInterface;

/**
 * The Login Interactor.
 */
public class LoginInteractor implements LoginInputBoundary {
    private final LoginUserDataAccessInterface userDataAccessObject;
    private final LoginOutputBoundary loginPresenter;
    private final UserFactory userFactory;
    private final UserPlaidDataAccessInterface plaidDataAccessObject;
    private final PlaidDataBaseUserAccessObjectInterface plaidDataBaseUserAccessObjectInterface;

    public LoginInteractor(LoginUserDataAccessInterface userDataAccessInterface,
                           LoginOutputBoundary loginOutputBoundary, UserFactory userFactory,
                           UserPlaidDataAccessInterface plaidDataAccessObject,
                           PlaidDataBaseUserAccessObjectInterface plaidDataBaseUserAccessObjectInterface) {
        this.userDataAccessObject = userDataAccessInterface;
        this.loginPresenter = loginOutputBoundary;
        this.userFactory = userFactory;
        this.plaidDataAccessObject = plaidDataAccessObject;
        this.plaidDataBaseUserAccessObjectInterface = plaidDataBaseUserAccessObjectInterface;
    }

    @Override
    public void execute(LoginInputData loginInputData) {
        final String username = loginInputData.getEmail();
        final String password = loginInputData.getPassword();
        final PlaidInputBoundary plaidInputBoundary = new PlaidInteractor(
                plaidDataAccessObject, plaidDataBaseUserAccessObjectInterface, userFactory);
        final PlaidController plaidController = new PlaidController(plaidInputBoundary);
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
