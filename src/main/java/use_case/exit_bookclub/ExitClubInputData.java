package use_case.exit_bookclub;

/**
 * Input data for join_club usecase.
 */
public class ExitClubInputData {

    private String userName;
    private String clubName;

    public ExitClubInputData(String userName, String clubName) {
        this.userName = userName;
        this.clubName = clubName;
    }

    public String getUsername() {
        return userName;
    }

    public String getClubName() {
        return clubName;
    }
}
