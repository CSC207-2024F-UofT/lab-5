package use_case.survey_second_page;

import java.util.ArrayList;

/**
 * Output Data for the Survey Use Case.
 */
public class SurveySecondPageOutputData {

    private final ArrayList<String> favoriteMovies;

    public SurveySecondPageOutputData(String firstMovie, String secondMovie, String thirdMovie, boolean useCaseFailed) {

        this.favoriteMovies = new ArrayList<>();
        this.favoriteMovies.add(firstMovie);
        this.favoriteMovies.add(secondMovie);
        this.favoriteMovies.add(thirdMovie);
    }

    public ArrayList<String> getFavoriteMovies() {
        return favoriteMovies;
    }
}
