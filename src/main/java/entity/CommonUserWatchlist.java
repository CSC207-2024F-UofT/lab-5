package entity;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

/**
 * A class representing a user-created watchlist of a User in this app.
 */
public class CommonUserWatchlist extends CommonWatchlist implements UserWatchlist{
    // TODO Find or write more specific exceptions to throw

    private String listName;
    private final List<Movie> movies = new ArrayList<>();

    public CommonUserWatchlist(String listName) {
        this.listName = listName;
    }

    /**
     * Sets the name of the watchlist.
     *
     * @param listname new name of the watchlist
     */
    @Override
    public void changeListName(String listname) {
        this.listName = listname;
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