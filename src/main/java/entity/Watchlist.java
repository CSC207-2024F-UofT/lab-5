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
     * Returns the movies within the list.
     * @return the movies in the list.
     */
    List<Movie> getMovies();
}
