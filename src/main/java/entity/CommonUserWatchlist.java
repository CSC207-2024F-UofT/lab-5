package entity;

import java.util.ArrayList;
import java.util.List;

/**
 * A class representing a user-created watchlist of a User in this app.
 */
public class CommonUserWatchlist extends CommonWatchlist implements UserWatchlist {

    private String listName;
    private List<Movie> movies;

    public CommonUserWatchlist(String listName) {
        this.listName = listName;
        this.movies = new ArrayList<>();
    }

    /**
     * Get the name of the watchlist.
     */
    @Override
    public String getListName() {
        return listName;
    }

    /**
     * Sets the name of the watchlist.
     * @param listname new name of the watchlist
     */
    @Override
    public void changeListName(String listname) {
        this.listName = listname;
    }

    /**
     * Get Movie at a given index in this watchlist.
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
