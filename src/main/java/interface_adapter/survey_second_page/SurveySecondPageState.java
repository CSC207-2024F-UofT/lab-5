package interface_adapter.survey_second_page;

import entity.User;

/**
 * Adds a selected movie to the favoriteMovies list if less than three movies are selected.
 */
public class SurveySecondPageState {
    private String firstMovie = "";
    private String secondMovie = "";
    private String thirdMovie = "";
    private String movieEntryError;

    public String getFirstMovie() {
        return firstMovie;
    }

    public String getSecondMovie() {
        return secondMovie;
    }

    public String getThirdMovie() {
        return thirdMovie;
    }

    public String getMovieEntryError() {
        return movieEntryError;
    }

    /**
     * Sets the first favorite movie.
     * @param firstMovie entry for first text field
     */
    public void setFirstMovie(String firstMovie) {
        this.firstMovie = firstMovie;
        validateMovies();
    }

    /**
     * Sets the second favorite movie.
     * @param secondMovie entry for second text field
     */
    public void setSecondMovie(String secondMovie) {
        this.secondMovie = secondMovie;
        validateMovies();
    }

    /**
     * Sets the third favorite movie.
     * @param thirdMovie entry for third text field
     */
    public void setThirdMovie(String thirdMovie) {
        this.thirdMovie = thirdMovie;
        validateMovies();
    }

    public void setMovieEntryError(String movieEntryError) {
        this.movieEntryError = movieEntryError;
    }

    /**
     * Returns whether submission is allowed.
     * @return true or false
     */
    public boolean canSubmit() {
        return !firstMovie.isEmpty() && !secondMovie.isEmpty() && !thirdMovie.isEmpty();
    }

    // Validates movie entries and sets error if necessary
    private void validateMovies() {
        if (!canSubmit()) {
            movieEntryError = "Please enter exactly 3 favorite movies.";
        }
        else {
            movieEntryError = null;
        }
    }

    @Override
    public String toString() {
        return "SurveySecondPageState{"
                + "firstMovie='" + firstMovie + '\''
                + ", secondMovie='" + secondMovie + '\''
                + ", thirdMovie='" + thirdMovie + '\''
                + ", movieEntryError='" + movieEntryError + '\''
                + '}';
    }

    /**
     * Set the current user who is using this survey.
     * @param user the user taking the survey.
     */
    public void setCurrentUser(User user) {

    }
}
