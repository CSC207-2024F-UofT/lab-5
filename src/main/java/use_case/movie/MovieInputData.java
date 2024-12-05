package use_case.movie;

import java.util.List;

import entity.CommonMovie;

/**
 * The Input Data for the Movie Use Case.
 */
public class MovieInputData {

    private final String movieTitle;
    private final List<CommonMovie> results;

    public MovieInputData(String movieTitle,
                          List<CommonMovie> results) {

        this.movieTitle = movieTitle;
        this.results = results;
    }

    public String getMovieTitle() {
        return movieTitle;
    }

    public List<CommonMovie> getResults() {
        return results;
    }

}
