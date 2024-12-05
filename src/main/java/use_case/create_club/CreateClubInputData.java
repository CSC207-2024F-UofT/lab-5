package use_case.create_club;

/**
 * Input data for the CreateClub usecase.
 */
public class CreateClubInputData {
    private final String clubName;
    private final String clubDescription;
    private String username;

    public CreateClubInputData(String clubName, String clubDescription, String username) {
        this.clubName = clubName;
        this.clubDescription = clubDescription;
        this.username = username;
    }

    public String getClubName() {
        return clubName;
    }

    public String getClubDescription() {
        return clubDescription;
    }

    public String getUsername() {
        return username;
    }
}
