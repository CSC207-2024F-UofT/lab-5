package interface_adapter.logout;

import use_case.logout.LogoutInputBoundary;
import use_case.logout.LogoutInputData;

/**
 * The controller for the Logout Use Case.
 */
public class LogoutController {

    private LogoutInputBoundary logoutUseCaseInteractor;

    public LogoutController(LogoutInputBoundary logoutUseCaseInteractor) {
        this.setLogoutUseCaseInteractor(logoutUseCaseInteractor);
    }

    /**
     * Executes the Logout Use Case.
     * @param username the username of the user logging in
     */
    public void execute(String username) {
        final LogoutInputData logoutInputData = new LogoutInputData(
                username);
        getLogoutUseCaseInteractor().execute(logoutInputData);
    }

    public LogoutInputBoundary getLogoutUseCaseInteractor() {
        return logoutUseCaseInteractor;
    }

    public void setLogoutUseCaseInteractor(LogoutInputBoundary logoutUseCaseInteractor) {
        this.logoutUseCaseInteractor = logoutUseCaseInteractor;
    }
}
