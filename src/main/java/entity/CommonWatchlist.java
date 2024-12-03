package entity;

import java.util.ArrayList;
import java.util.List;

/**
 * A class representing a watched list of a User in this app.
 */
public class CommonWatchlist implements Watchlist {

    // TODO Find or write more specific exceptions to throw

    private final String listName = "pwl";
    private final List<Movie> movies = new ArrayList<>();

    /**
     * Get the name of this watchlist.
     *
     * @return the name of the watchlist
     */
    public String getListName() {
        return this.listName;
    }

    /**
     * Check if the watchlist contains a movie.
     * @param movie to look for
     * @return if movie is in this watchlist
     */
    public boolean contains(Movie movie) {
        return this.movies.contains(movie);
    }

    /**
     * Add a movie to this watchlist.
     *
     * @param movie Movie to be added to the list
     * @throws Exception if the Movie is already in the list
     */
    public void addMovie(Movie movie) throws Exception {
        if (!this.movies.contains(movie)) {
            this.movies.add(movie);
        }
        else {
            throw new Exception("Movie is already in this list");
        }
    }

    /**
     * Remove a movie from this watchlist.
     * @param movie a Movie to be removed
     * @throws Exception if the movie is not found in the list
     */
    public void removeMovie(Movie movie) throws Exception {
        if (this.movies.contains(movie)) {
            this.movies.remove(movie);
        }
        else {
            throw new Exception("Movie not found in this list");
        }
    }

    /**
     * Get Movie at a given index in this watchlist.
     *
     * @param index of a Movie
     * @return Movie at a given index
     * @throws IndexOutOfBoundsException if index is out of bounds
     */
    @Override
    public Movie getMovie(int index) throws IndexOutOfBoundsException {
        return this.movies.get(index);
    }

    /**
     * Get length of this watchlist.
     *
     * @return length of the watchlist
     */
    @Override
    public int size() {
        return this.movies.size();
    }
}
