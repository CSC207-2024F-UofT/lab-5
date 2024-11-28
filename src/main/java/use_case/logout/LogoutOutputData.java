package use_case.logout;

/**
 * Output Data for the Logout Use Case.
 */
public class LogoutOutputData {

    private String username;
    private boolean useCaseFailed;

    public LogoutOutputData(String username, boolean useCaseFailed) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public boolean isUseCaseFailed() {
        return useCaseFailed;
    }
}
