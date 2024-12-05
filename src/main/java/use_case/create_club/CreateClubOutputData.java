package use_case.create_club;

/**
 * Output data for the CreateClub usecase.
 *
 */
public class CreateClubOutputData {
    private final String username;
    private final String clubname;
    private final boolean usecaseFailed;
    private final String clubDes;

    public CreateClubOutputData(String username, String clubname, String clubDes, boolean usecaseFailed) {
        this.username = username;
        this.clubname = clubname;
        this.usecaseFailed = usecaseFailed;
        this.clubDes = clubDes;
    }

    public String getClubDes() {
        return clubDes;
    }

    public String getUsername() {
        return username;
    }

    public String getClubname() {
        return clubname;
    }
}
