package entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * A simple implementation of the Watchlist interface.
 */
public class CommonWatchlist implements Watchlist {

    private final String name;
    private final List<Movie> moviesList;

    public CommonWatchlist(String name, List<Movie> moviesList) {
        this.name = name;
        this.moviesList = moviesList;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public List<Movie> getMovies() {
        return moviesList;
    }
}
