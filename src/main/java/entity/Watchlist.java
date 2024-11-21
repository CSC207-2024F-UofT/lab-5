package entity;

import java.util.List;

/**
 * The representation of a watchlist in our program.
 */
public interface Watchlist {
    /**
     * Returns the name of the watchlist.
     * @return the name of the watchlist.
     */
    String getName();

    /**
     * Sets the name of the watchlist.
     * @param name the name of the watchlist.
     */
    void setName(String name);

    /**
     * Returns the movies within the list.
     * @return the movies in the list.
     */
    List<Movie> getMovies();

    /**
     * Adds movie to list.
     * @param movie movie to add.
     */
    void addMovie(Movie movie);

    /**
     * Returns the movie in the list.
     * @param movieName movie looking
     * @return true if movie in list.
     */
    boolean movieExists(String movieName);

}
