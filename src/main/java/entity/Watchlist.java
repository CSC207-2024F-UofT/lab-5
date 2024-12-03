package entity;

/**
 * The representation of a previously watched list in our program.
 */
public interface Watchlist {



    // TODO Find or write more specific exceptions to throw
    /**
     * Get the name of this watchlist.
     * @return the name of the watchlist
     */
    String getListName();

    /**
     * Check if the watchlist contains a movie.
     * @param movie to look for
     * @return if movie is in this watchlist
     */
    boolean contains(Movie movie);

    /**
     * Get Movie at a given index in this watchlist.
     * @param index of a Movie
     * @return Movie at a given index
     * @throws IndexOutOfBoundsException if index is out of bounds
     */
    Movie getMovie(int index) throws IndexOutOfBoundsException;

    /**
     * Get length of this watchlist.
     * @return length of the watchlist
     */
    int size();

    /**
     * Add a movie to this watchlist.
     * @param movie Movie to be added to the list
     * @throws Exception if movie is already in the list
     */
    void addMovie(Movie movie) throws Exception;

    /**
     * Remove a movie from this watchlist.
     * @param movie a Movie to be removed
     * @throws Exception if the movie is not found in the list
     */
    void removeMovie(Movie movie) throws Exception;
}
