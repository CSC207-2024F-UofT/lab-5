package use_case.movie;

import java.util.List;

/**
 * Output Data for the Movie Use Case.
 */
public class MovieOutputData {

    private final String movieName;
    private final String posterPath;
    private final String overview;
    private final int voteAverage;
    private final List<String> genres;

    public MovieOutputData(String movieName, String posterPath,
                           String overview, int voteAverage,
                           List<String> genres) {

        this.movieName = movieName;
        this.posterPath = posterPath;
        this.overview = overview;
        this.voteAverage = voteAverage;
        this.genres = genres;
    }

    public String getMovieName() {
        return movieName;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public String getOverview() {
        return overview;
    }

    public int getVoteAverage() {
        return voteAverage;
    }

    public List<String> getGenres() {
        return genres;
    }
}
