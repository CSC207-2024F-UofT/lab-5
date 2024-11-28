package interface_adapter.logout;

import use_case.logout.LogoutInputBoundary;
import use_case.logout.LogoutInputData;

/**
 * The controller for the Logout Use Case.
 */
public class LogoutController {

    private LogoutInputBoundary logoutUseCaseInteractor;

    public LogoutController(LogoutInputBoundary logoutUseCaseInteractor) {
        // Yoli - TODO: Save the interactor in the instance variable.
        this.logoutUseCaseInteractor = logoutUseCaseInteractor;
    }

    /**
     * Executes the Logout Use Case.
     * @param username the username of the user logging in
     */
    public void execute(String username) {
        // Yoli - TODO: run the use case interactor for the logout use case
        // 1. instantiate the `LogoutInputData`, which should contain the username.
        final LogoutInputData logoutInputData = new LogoutInputData(username);
        // 2. tell the Interactor to execute.
        logoutUseCaseInteractor.execute(logoutInputData);
    }
}
