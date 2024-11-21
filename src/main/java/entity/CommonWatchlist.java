package entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * A simple implementation of the Watchlist interface.
 */
public class CommonWatchlist implements Watchlist {

    private String name;
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
    public void setName(String n) {
        name = n;
    }

    @Override
    public List<Movie> getMovies() {
        return moviesList;
    }

    @Override
    public void addMovie(Movie movie) {
        moviesList.add(movie);
    }

    @Override
    public boolean movieExists(String movieName) {
        final List<String> movieNames = new ArrayList<>();
        for (Movie movie : moviesList) {
            final String title = movie.getTitle();
            movieNames.add(title);
        }
        return movieNames.contains(movieName);
    }
}
