package interface_adapter.survey_one;

/**
 * The state for the Survey1 View Model.
 */
public class Survey1State {
    private String username = "";
    private String survey1Error;
    private String genre1 = "";
    private String genre2 = "";
    private String genre3 = "";

    public Survey1State(Survey1State copy) {
        username = copy.username;
        genre1 = copy.genre1;
        genre2 = copy.genre2;
        genre3 = copy.genre3;
    }

    public Survey1State() {
    }

    public String getGenre1() {
        return genre1;
    }

    public String getGenre2() {
        return genre2;
    }

    public String getGenre3() {
        return genre3;
    }

    public void setGenre1(String genre1) {
        this.genre1 = genre1;
    }

    public void setGenre2(String genre2) {
        this.genre2 = genre2;
    }

    public void setGenre3(String genre3) {
        this.genre3 = genre3;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public String getSurvey1Error() {
        return survey1Error;
    }

    public void setSurvey1Error(String survey1Error) {
        this.survey1Error = survey1Error;
    }
}
