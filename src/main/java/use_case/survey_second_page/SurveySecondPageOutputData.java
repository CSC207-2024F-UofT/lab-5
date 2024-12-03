package use_case.survey_second_page;

import java.util.ArrayList;
import java.util.List;

import entity.CommonMovie;

/**
 * Output Data for the Survey Use Case.
 */
public class SurveySecondPageOutputData {

    private final ArrayList<List<String>> favoriteGenres;
    private final ArrayList<String> favoriteMovies;

    private final boolean useCaseFailed;

    public SurveySecondPageOutputData(String firstMovie, String secondMovie, String thirdMovie, boolean useCaseFailed) {

        this.favoriteGenres = new ArrayList<>();
        this.favoriteMovies = new ArrayList<>();
        this.favoriteMovies.add(firstMovie);
        this.favoriteMovies.add(secondMovie);
        this.favoriteMovies.add(thirdMovie);

        this.useCaseFailed = useCaseFailed;
    }

    public ArrayList<String> getFavoriteMovies() {
        return favoriteMovies;
    }
}
