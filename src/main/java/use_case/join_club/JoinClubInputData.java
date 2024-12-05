package use_case.join_club;

/**
 * Input data for join_club usecase.
 */
public class JoinClubInputData {
    private String username;
    private String clubName;

    public JoinClubInputData(String username, String clubName) {
        this.username = username;
        this.clubName = clubName;

    }

    public String getUsername() {
        return username;
    }

    public String getClubName() {
        return clubName;
    }
}
