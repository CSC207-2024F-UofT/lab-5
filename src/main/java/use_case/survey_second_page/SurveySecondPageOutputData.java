package use_case.survey_second_page;

import java.util.ArrayList;
import java.util.List;

import entity.CommonMovie;

/**
 * Output Data for the Survey Use Case.
 */
public class SurveySecondPageOutputData {

    private final ArrayList<List<String>> favoriteGenres;
    private final ArrayList<CommonMovie> favoriteMovies;


    private final boolean useCaseFailed;

    public SurveySecondPageOutputData(String firstMovie, String secondMovie, String thirdMovie, boolean useCaseFailed) {
        final CommonMovie first = new CommonMovie(firstMovie);
        final CommonMovie second = new CommonMovie(secondMovie);
        final CommonMovie third = new CommonMovie(thirdMovie);

        this.favoriteMovies = new ArrayList<>();
        favoriteMovies.add(first);
        favoriteMovies.add(second);
        favoriteMovies.add(third);

        this.favoriteGenres = new ArrayList<>();
        favoriteGenres.add(first.getGenres());
        favoriteGenres.add(second.getGenres());
        favoriteGenres.add(third.getGenres());

        this.useCaseFailed = useCaseFailed;
    }

    public ArrayList<List<String>> getFavoriteGenres() {
        return favoriteGenres;
    }

    public ArrayList<CommonMovie> getFavoriteMovies() {
        return favoriteMovies;
    }

    public boolean isUseCaseFailed() {
        return useCaseFailed;
    }
}
