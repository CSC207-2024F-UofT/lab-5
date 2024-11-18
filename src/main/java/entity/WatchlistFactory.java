package entity;

import java.util.List;

/**
 * Factory for creating watchlists.
 */
public interface WatchlistFactory {
    /**
     * Creates a new Watchlist.
     * @param title the name of the list
     * @param moviesList list of movies in the list
     * @return the new list
     */
    Watchlist create(String title, List<Movie> moviesList);
}
