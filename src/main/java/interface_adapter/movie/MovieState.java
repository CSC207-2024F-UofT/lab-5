package interface_adapter.movie;

import java.util.ArrayList;
import java.util.List;

import entity.CommonMovie;
import entity.Movie;
import entity.User;

/**
 * The state for the Movie View Model.
 */
public class MovieState {
    private User currentUser;
    private Movie currentMovie;
    private boolean watched;
    private String title = "";
    private String posterPath = "";
    private String overview = "";
    private String externalStarRating = "";
    private List<String> genres = new ArrayList<>();

    private Double starRating;
    private List<String> reviews;

    private String movieError;

    private String emptyWatchlistsError = "You don't have any watchlists yet!";

    public MovieState() {
    }

    /**
     * Executes the Search Results Use Case.
     * @param aCurrentUser the current user
     * @param aCurrentMovie the current movie
     * @param watchedData if the movie was watched
     *
     */
    public void update(User aCurrentUser, Movie aCurrentMovie, boolean watchedData) {
        this.currentUser = aCurrentUser;
        this.currentMovie = aCurrentMovie;
        this.watched = watchedData;

        this.title = currentMovie.getTitle();
        this.posterPath = currentMovie.getPoster();
        this.overview = currentMovie.getOverview();
        this.externalStarRating = currentMovie.getVoterAverage();
        this.genres = currentMovie.getGenres();

        this.starRating = currentMovie.getStarRatings();
        this.reviews = currentMovie.getUserReviews();
    }

    public User getCurrentUser() {
        return this.currentUser;
    }

    public void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
    }

    public void setCurrentMovie(CommonMovie currentMovie) {
        this.currentMovie = currentMovie;
    }

    public Movie getCurrentMovie() {
        return this.currentMovie;
    }

    // Information for the view

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setPosterPath(String posterPath) {
        this.posterPath = posterPath;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getOverview() {
        return overview;
    }

    public void setVoteAverage(String voteAverage) {
        this.externalStarRating = voteAverage;
    }

    public List<String> getReviews() {
        return reviews;
    }

    public String getExternalStarRating() {
        return externalStarRating;
    }

    public void setGenres(List<String> genres) {
        this.genres = genres;
    }

    public List<String> getGenres() {
        return genres;
    }

    public void setMovieError(String movieError) {
        this.movieError = movieError;
    }

    public String getMovieError() {
        return movieError;
    }

    public void setEmptyWatchlistsError(String emptyWatchlistsError) {
        this.emptyWatchlistsError = emptyWatchlistsError;
    }

    public String getEmptyWatchlistsError() {
        return emptyWatchlistsError;
    }

    public void setStarRating(Double starRating) {
        this.starRating = starRating;
    }

    public String getStarRating() {
        return String.valueOf(starRating);
    }

    /**
     * Returns a string representation of the MovieState object.
     *
     * @return a string representation of the MovieState object
     */
    @Override
    public String toString() {
//        return "MovieState{"
//                + "currentUser='" + currentUser
//                + ", title='" + title
//                + ", starRating=" + starRating
//                + ", reviews=" + reviews
//                + ", externalStarRating='" + externalStarRating
//                + ", genres=" + genres
//                + '}';
        return null;
    }
}
