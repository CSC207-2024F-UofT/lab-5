package entity;

import data_access.GenreMap;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.mongodb.client.model.Filters.eq;



/**
 * A simple implementation of the User interface.
 */
public class CommonUser implements User {

    private final String name;
    private final String password;
    // TODO update the interface

    // TODO I don't think storing login status is useful.
    // We store current user in the view states already
    // because we need to access their instance attributes.

    private boolean loginStatus;
    private Map<String, Integer> preferredGenres = new HashMap<>();
    private Watchlist pwl = new CommonWatchlist();
    private ArrayList<UserWatchlist> watchlists = new ArrayList<>();

    private List<MovieReview> ratingsAndReviews = new ArrayList<>();

    /**
     * Constructor for a CommonUser.
     * @param name the name of the user
     * @param password the password of the user
     */
    public CommonUser(String name, String password) {
        this.name = name;
        this.password = password;
        this.loginStatus = false;

        final GenreMap genreMap = new GenreMap();
        for (String genre : genreMap.keySet()) {
            this.preferredGenres.put(genre, 0);
        }
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public boolean getLoginStatus() {
        return loginStatus;
    }

    /**
     * Get the previously watched list of this User.
     * @return previously watched list
     */
    @Override
    public Watchlist getPwl() {
        return this.pwl;
    }

    /**
     * Returns the user created watchlists of the user.
     *
     * @return list of watchlists of the user.
     */
    @Override
    public ArrayList<UserWatchlist> getWatchlists() {
        return this.watchlists;
    }

    @Override
    public Map<String, Integer> getPreferredGenres() {
        return this.preferredGenres;
    }

    /**
     * Adds a genre to the list of preferred genres of this User.
     * @param genre a genre to add to the list
     */
    @Override
    public void addPreferredGenres(String genre) {
        this.preferredGenres.put(genre, this.preferredGenres.get(genre) + 1);
    }

    /**
     * Adds a list of genres to the map of preferred genres of this User.
     * @param genres a list of genres
     */
    @Override
    public void addPreferredGenres(List<String> genres) {
        for (String genre : genres) {
            this.preferredGenres.put(genre, this.preferredGenres.get(genre) + 1);
        }
    }

    /**
     * Get reviews written by this User.
     * @return list of reviews
     */
    @Override
    public List<MovieReview> getRatingsAndReviews() {
        return this.ratingsAndReviews;
    }

    /**
     * Check if this User watched the movie before.
     *
     * @param movie in question
     * @return if the user watched this movie
     */
    @Override
    public boolean watchedBefore(Movie movie) {
        return this.pwl.contains(movie);
    }
}
