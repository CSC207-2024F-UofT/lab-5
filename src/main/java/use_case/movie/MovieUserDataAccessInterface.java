package use_case.movie;

import entity.Movie;

/**
 * DAO for the Movie Use Case.
 */
public interface MovieUserDataAccessInterface {

    /**
     * Checks if the given title exists.
     * @param title the title to look for
     * @return true if a movie with the given title exists; false otherwise
     * @throws Exception
     */
    boolean existsByName(String title) throws Exception;

    /**
     * Returns the movie with a given title.
     * @param title the user to save
     * @return the movie with the given title
     */
    Movie getMovieByName(String title);
}
