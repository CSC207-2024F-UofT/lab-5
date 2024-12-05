package interface_adapter.create_club;

/**
 * CreateClubState for the CreateClub usecase.
 */
public class CreateClubState {
    private String username;
    private String clubName;
    private String successMessage;
    private String clubDescription;
    private String errorMessage;

    public String getClubDescription() {
        return clubDescription;
    }

    public void setClubDescription(String clubDescription1) {
        this.clubDescription = clubDescription1;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username1) {
        this.username = username1;
    }

    public String getBookclub() {
        return clubName;
    }

    public void setBookclub(String clubName1) {
        this.clubName = clubName1;
    }

    public String getSuccessMessage() {
        return successMessage;
    }

    public void setSuccessMessage(String successMessage1) {
        this.successMessage = successMessage1;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage1) {
        this.errorMessage = errorMessage1;
    }
}
