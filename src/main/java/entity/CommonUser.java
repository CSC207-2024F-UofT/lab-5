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

    private boolean loginStatus;
    private Map<String, Integer> preferredGenres = new HashMap<>();
    private CommonUserWatchlistFactory watchlistFactory;
    private Watchlist pwl;
    private ArrayList<UserWatchlist> watchlists;

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

        // initializing watchlists for the user
        this.watchlists = new ArrayList();

        // pwl as default separate watchlist
        this.watchlistFactory = new CommonUserWatchlistFactory();
        this.pwl = watchlistFactory.create("Previously Watched");

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

    @Override
    public void setPwl(Watchlist watchlist) {
        this.pwl = watchlist;
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
    public UserWatchlist getWatchlist(String watchlistName) {
        UserWatchlist target = null;
        for (UserWatchlist watchlist : watchlists) {
            if (watchlist.getListName().equals(watchlistName)) {
                target = watchlist;
            }
        }
        return target;
    }

    @Override
    public void setWatchlists(List<UserWatchlist> watchlists) {
        this.watchlists = new ArrayList<>(watchlists);
    }

    @Override
    public void addWatchlist(UserWatchlist watchlist) {
        this.watchlists.add(watchlist);
    }

    @Override
    public Map<String, Integer> getPreferredGenres() {
        return this.preferredGenres;
    }

    @Override
    public void setPreferredGenres(Map<String, Integer> preferredGenres) {
        this.preferredGenres = preferredGenres;
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
