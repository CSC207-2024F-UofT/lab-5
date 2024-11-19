package use_case.survey1;

/**
 * Output Data for the Survey1 Use Case.
 */
public class Survey1OutputData {
    private final String genre1;
    private final String genre2;
    private final String genre3;

    public Survey1OutputData(String genre1, String genre2, String genre3) {
        this.genre1 = genre1;
        this.genre2 = genre2;
        this.genre3 = genre3;
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
}
