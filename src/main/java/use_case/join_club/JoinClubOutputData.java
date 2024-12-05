package use_case.join_club;

/**
 * Output data for the Join Club Use Case.
 */
public class JoinClubOutputData {
    private final String username;
    private final String clubName;
    private boolean useCaseFailed;

    public JoinClubOutputData(String username, String clubName, boolean useCaseFailed) {
        this.username = username;
        this.clubName = clubName;
        this.useCaseFailed = useCaseFailed;
    }

    public String getUsername() {
        return username;
    }

    public String getClubName() {
        return clubName;
    }

    public boolean isUseCaseFailed() {
        return useCaseFailed;
    }
}
