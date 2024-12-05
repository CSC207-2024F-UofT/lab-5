package entity;

import java.util.List;

/**
 * The representation of a movie in our program.
 */
public interface Movie {

    /**
     * Returns the title of the movie.
     * @param posterPath the posterPath of the movie.
     * @param overview the overview of the movie.
     * @param voteAverage the voteAverage of the movie.
     * @param genres the genres of the movie.
     * @param movieId the Id of the movie in the database
     *
     */
    void setInformation(String posterPath, String overview, String voteAverage, List<String> genres, int movieId);

    /**
     * Return the ID of this movie.
     * @return an int.
     */
    int getMovieId();

    /**
     * Returns the title of the movie.
     * @return the title of the movie.
     */
    String getTitle();

    /**
     * Returns the poster path of the movie.
     * @return the poster path of the movie.
     */
    String getPoster();

    /**
     * Returns the overview/summary of the movie.
     * @return the overview of the movie.
     */
    String getOverview();

    /**
     * Returns the average voter ratings of the movie.
     * @return the average voter ratings of the movie.
     */
    String getVoterAverage();

    /**
     * Returns the list of genres of the movie.
     * @return the list of genres of the movie.
     */
    List<String> getGenres();

    /**
     * Returns the star ratings of the movie.
     * @return the star ratings of the movie.
     */
    Double getStarRatings();

    /**
     * Returns the reviews of the movie from users.
     * @return the reviews of the movie from users.
     */
    List<String> getUserReviews();
}
