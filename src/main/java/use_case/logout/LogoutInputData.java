package use_case.logout;

/**
 * The Input Data for the Logout Use Case.
 */
public class LogoutInputData {
    private final String currentUser;

    public LogoutInputData(String username) {
        this.currentUser = username;
    }

    public String getCurrentUser() {
        return currentUser;
    }
}
