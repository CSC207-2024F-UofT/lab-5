package entity;

import java.util.List;

/**
 * Factory for creating CommonWatchlist objects.
 */
public class CommonWatchlistFactory implements WatchlistFactory {

    @Override
    public Watchlist create(String name, List<Movie> moviesList) {
        return new CommonWatchlist(name, moviesList);
    }
}
