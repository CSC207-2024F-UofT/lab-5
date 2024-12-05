package entity;

import java.util.ArrayList;
import java.util.List;

import org.jetbrains.annotations.NotNull;

/**
 * A simple implementation of the Movie interface.
 */
public class CommonMovie implements Movie, Comparable {

    private final String title;
    private String posterPath;
    private String overview;
    private String voterAverage;
    private List<String> genres;

    private final List<String> userReviews;
    private final Double starRating;
    private int movieId;

    // Constructor with default values
    public CommonMovie(String title) {
        this.title = title;

        this.starRating = 0.0;
        this.userReviews = new ArrayList<>();
    }

    @Override
    public void setInformation(String posterPaths, String overviews, String voteAverages, List<String> genresList,
                               int movieID) {
        this.posterPath = posterPaths;
        this.overview = overviews;
        this.voterAverage = voteAverages;
        this.genres = genresList;
        this.movieId = movieID;
    }

    @Override
    public int getMovieId() {
        return movieId;
    }

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public String getPoster() {
        return posterPath;
    }

    @Override
    public String getOverview() {
        return overview;
    }

    @Override
    public String getVoterAverage() {
        return voterAverage;
    }

    @Override
    public Double getStarRatings() {
        return starRating;
    }

    @Override
    public List<String> getUserReviews() {
        return userReviews;
    }

    @Override
    public List<String> getGenres() {
        return genres;
    }

    /**
     * Compares this object with the specified object for order.  Returns a
     * negative integer, zero, or a positive integer as this object is less
     * than, equal to, or greater than the specified object.
     *
     * <p>The implementor must ensure {@link Integer#signum
     * signum}{@code (x.compareTo(y)) == -signum(y.compareTo(x))} for
     * all {@code x} and {@code y}.  (This implies that {@code
     * x.compareTo(y)} must throw an exception if and only if {@code
     * y.compareTo(x)} throws an exception.)
     *
     * <p>The implementor must also ensure that the relation is transitive:
     * {@code (x.compareTo(y) > 0 && y.compareTo(z) > 0)} implies
     * {@code x.compareTo(z) > 0}.
     *
     * <p>Finally, the implementor must ensure that {@code
     * x.compareTo(y)==0} implies that {@code signum(x.compareTo(z))
     * == signum(y.compareTo(z))}, for all {@code z}.
     *
     * @param o the object to be compared.
     * @return a negative integer, zero, or a positive integer as this object
     * is less than, equal to, or greater than the specified object.
     * @throws NullPointerException if the specified object is null
     * @throws ClassCastException   if the specified object's type prevents it
     *                              from being compared to this object.
     * @apiNote It is strongly recommended, but <i>not</i> strictly required that
     * {@code (x.compareTo(y)==0) == (x.equals(y))}.  Generally speaking, any
     * class that implements the {@code Comparable} interface and violates
     * this condition should clearly indicate this fact.  The recommended
     * language is "Note: this class has a natural ordering that is
     * inconsistent with equals."
     */
    @Override
    public int compareTo(@NotNull Object o) {
        return 0;
    }
}
