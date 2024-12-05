package use_case.home;

/**
 * Output Data for the Change Password Use Case.
 */
public class HomeOutputData {

    private final String username;

    private final boolean useCaseFailed;

    public HomeOutputData(String username, boolean useCaseFailed) {
        this.username = username;
        this.useCaseFailed = useCaseFailed;
    }

    public String getUsername() {
        return username;
    }

    public boolean isUseCaseFailed() {
        return useCaseFailed;
    }

}
