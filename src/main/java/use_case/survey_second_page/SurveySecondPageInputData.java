package use_case.survey_second_page;

/**
 * The Input Data for the Login Use Case.
 */
public class SurveySecondPageInputData {
    private final String firstMovie;
    private final String secondMovie;
    private final String thirdMovie;

    public SurveySecondPageInputData(String firstMovie, String secondMovie, String thirdMovie) {
        this.firstMovie = firstMovie;
        this.secondMovie = secondMovie;
        this.thirdMovie = thirdMovie;
    }

    public String getFirstMovie() {
        return firstMovie;
    }

    public String getSecondMovie() {
        return secondMovie;
    }

    public String getThirdMovie() {
        return thirdMovie;
    }
}
