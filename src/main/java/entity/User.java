package entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * The representation of a user in our program.
 */
public interface User {

    /**
     * Returns the username of the user.
     *
     * @return the username of the user.
     */
    String getName();

    /**
     * Returns the password of the user.
     *
     * @return the password of the user.
     */
    String getPassword();

    /**
     * Returns the login status of the user.
     *
     * @return the login status of the user.
     */
    boolean getLoginStatus();

    /**
     * Returns the user created watchlists of the user.
     *
     * @return list of watchlists of the user.
     */
    ArrayList<UserWatchlist> getWatchlists();

    UserWatchlist getWatchlist(String watchlistName);

    /**
     * Returns the user created watchlists of the user.
     *
     * @param watchlists watchlists of the user.
     */
    void setWatchlists(List<UserWatchlist> watchlists);

    /**
     * Returns the preferred genres of the user.
     * @param preferredGenres preferred genres of the user.
     */
    void setPreferredGenres(Map<String, Integer> preferredGenres);

    /**
     * Adds new watchlist to user.
     * @param  watchlist watchlist to add
     */
    void addWatchlist(UserWatchlist watchlist);

    /**
     * Get the previously watched list of this User.
     *
     * @return previously watched list
     */
    Watchlist getPwl();

    /**
     * Sets pwl.
     * @param watchlist pwl
     */
    void setPwl(Watchlist watchlist);

    /**
     * Adds a genre to the list of preferred genres of this User.
     *
     * @param genre a genre to add to the list
     */
    void addPreferredGenres(String genre);

    /**
     * Adds a list of genres to the list of preferred genres of this User.
     *
     * @param genres a list of genres
     */
    void addPreferredGenres(List<String> genres);

    /**
     * Get reviews written by this User.
     *
     * @return list of reviews
     */
    List<MovieReview> getRatingsAndReviews();

    /**
     * Check if this User watched the movie before.
     *
     * @param movie in question
     * @return if the user watched this movie
     */
    boolean watchedBefore(Movie movie);

    /**
     * Returns the preferred genres of the user.
     *
     * @return map of preferred genres of the user.
     */
    Map<String, Integer> getPreferredGenres();

}