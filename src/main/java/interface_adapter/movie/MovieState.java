package interface_adapter.movie;

import java.util.ArrayList;
import java.util.List;

import entity.Movie;
import entity.User;

/**
 * The state for the Login View Model.
 */
public class MovieState {
    private User currentUser;
    private Movie currentMovie;
    private boolean watched;
    private String title = "";
    private Double starRating;
    private List<String> reviews;
    private String externalStarRating = "";
    private List<String> genres = new ArrayList<>();
    private String movieError;

    public MovieState() {
    }

    public void update(User currentUser, Movie currentMovie, boolean watched) {
        this.currentUser = currentUser;
        this.currentMovie = currentMovie;
        this.watched = watched;
        this.title = currentMovie.getTitle();
        this.starRating = currentMovie.getStarRatings();
        this.reviews = currentMovie.getUserReviews();
        this.externalStarRating = currentMovie.getVoterAverage();
        this.genres = currentMovie.getGenres();
    }

    public User getCurrentUser() {
        return this.currentUser;
    }

    // added
    public void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
    }

    public Movie getCurrentMovie() {
        return this.currentMovie;
    }

    public String getTitle() {
        return title;
    }

    public String getStarRating() {
        return String.valueOf(starRating);
    }

    public List<String> getReviews() {
        return reviews;
    }

    public String getExternalStarRating() {
        return externalStarRating;
    }

    public List<String> getGenres() {
        return genres;
    }

    public void setMovieError(String movieError) {
        this.movieError = movieError;
    }

    /**
     * Returns a string representation of the MovieState object.
     *
     * @return a string representation of the MovieState object
     */
    @Override
    public String toString() {
        return "MovieState{"
                + "currentUser='" + currentUser
                + ", title='" + title
                + ", starRating=" + starRating
                + ", reviews=" + reviews
                + ", externalStarRating='" + externalStarRating
                + ", genres=" + genres
                + '}';
    }
}
