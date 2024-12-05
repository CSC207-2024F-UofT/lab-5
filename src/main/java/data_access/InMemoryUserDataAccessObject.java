package data_access;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import entity.*;
import use_case.add_to_watchlist.AddToWatchlistDataAccessInterface;
import use_case.create_watchlist.CreateWatchlistDataAccessInterface;
import use_case.home.HomeUserDataAccessInterface;
import use_case.login.LoginUserDataAccessInterface;
import use_case.logout.LogoutUserDataAccessInterface;
import use_case.my_reviews.MyReviewsDataAccessInterface;
import use_case.signup.SignupUserDataAccessInterface;
import use_case.survey_one.Survey1UserDataAccessInterface;

import use_case.survey_second_page.SurveySecondPageDataAccessInterface;
import use_case.watchlist.WatchlistUserDataAccessInterface;
import use_case.watchlists.WatchlistsUserDataAccessInterface;
import use_case.watchlists.delete.DeleteWatchlistUserDataAccessInterface;
import use_case.watchlists.rename.RenameUserDataAccessInterface;


/**
 * In-memory implementation of the DAO for storing user data. This implementation does
 * NOT persist data between runs of the program.
 */
public class InMemoryUserDataAccessObject implements SignupUserDataAccessInterface,
        LoginUserDataAccessInterface,
        HomeUserDataAccessInterface,
        MyReviewsDataAccessInterface,
        LogoutUserDataAccessInterface,
        WatchlistsUserDataAccessInterface,
        Survey1UserDataAccessInterface,
        SurveySecondPageDataAccessInterface,
        RenameUserDataAccessInterface,
        DeleteWatchlistUserDataAccessInterface,
        WatchlistUserDataAccessInterface,
        AddToWatchlistDataAccessInterface,
        CreateWatchlistDataAccessInterface {

    private final Map<String, User> users = new HashMap<>();
    private final Map<String, List<Watchlist>> userWatchlists = new HashMap<>();
    private final Map<String, List<Movie>> watchlistMovies = new HashMap<>();
    private final Map<String, List<Movie>> userPwl = new HashMap<>();
    private final Map<String, List<MovieReview>> userReviews = new HashMap<>();
    private String currentUsername;

    @Override
    public boolean existsByName(String identifier) {
        return users.containsKey(identifier);
    }

    @Override
    public boolean movieExists(String movie) {
        return false;
    }

    @Override
    public void save(User user) {
        users.put(user.getName(), user);
    }

    @Override
    public User get(String username) {
        return users.get(username);
    }

    @Override
    public boolean savePreferredGenres(User user, Map<String, Integer> preferredGenres) {
        if (user == null || preferredGenres == null) {
            System.out.println("InMemoryUserDataAccessObject: User or preferredGenres is null.");
            return false;
        }

        // Update the user's preferredGenres
        try {
            System.out.println("InMemoryUserDataAccessObject: Saving preferred genres for user: " + user.getName());
            System.out.println("Preferred genres: " + preferredGenres);

            // Iterate over the preferredGenres map and update the user's genres
            for (Map.Entry<String, Integer> entry : preferredGenres.entrySet()) {
                String genre = entry.getKey();
                int count = entry.getValue();

                // Increment the genre count using the user's addPreferredGenres method
                for (int i = 0; i < count; i++) {
                    user.addPreferredGenres(genre);
                }
            }

            // Reflect the changes in the in-memory user repository
            users.put(user.getName(), user);

            System.out.println("Preferred genres saved successfully for user: " + user.getName());
            return true;
        } catch (Exception e) {
            System.err.println("InMemoryUserDataAccessObject: Error saving preferred genres: " + e.getMessage());
            return false;
        }
    }

    @Override
    public void savePwl(User user) {
        // Check if the user already exists
        if (!users.containsKey(user.getName())) {
            throw new IllegalArgumentException("User not found: " + user.getName());
        }

        if (!userPwl.containsKey(user.getName())) {
            userPwl.put(user.getName(), user.getPwl().getMovies());
        }
        else {
            userPwl.put(user.getName(), user.getPwl().getMovies());
        }
    }

    @Override
    public boolean saveWatchlist(User user, UserWatchlist watchlist) {
        boolean success = true;
        if (!users.containsKey(user.getName())) {
            success = false;
            throw new IllegalArgumentException("User not found: " + user.getName());
        }

        // Add the movie to the specific watchlist
        userWatchlists.computeIfAbsent(watchlist.getListName(), k -> new ArrayList<>()).add(watchlist);
        return success;
    }

    @Override
    public boolean saveToWatchlist(User user, int ind, Movie movie) {
        final String watchlistName = user.getWatchlists().get(ind).getListName();
        boolean success = true;
        // Check if the user exists
        if (!users.containsKey(user.getName())) {
            success = false;
            throw new IllegalArgumentException("User not found: " + user.getName());
        }

        if (user.getWatchlist(watchlistName) == null) {
            success = false;
            throw new IllegalArgumentException("Watchlist not found: " + watchlistName);
        }

        // Add the movie to the specific watchlist
        watchlistMovies.computeIfAbsent(watchlistName, k -> new ArrayList<>()).add(movie);
        return success;
    }

    @Override
    public boolean saveToPwl(User user, Movie movie) {
        boolean success = true;
        // Check if the user exists
        if (!users.containsKey(user.getName())) {
            success = false;
            throw new IllegalArgumentException("User not found: " + user.getName());
        }

        // Add the movie to the user's personal watchlist (Pwl)
        userPwl.computeIfAbsent(user.getName(), k -> new ArrayList<>()).add(movie);
        return success;
    }

    /**
     * Adds a review to this use object for testing purposes.
     * @param user A mock user object for testing.
     * @param review A mock review object for testing.
     */
    public void addReview(User user, MovieReview review) {
        if (!users.containsKey(user.getName())) {
            throw new IllegalArgumentException("User not found: " + user.getName());
        }
        userReviews.computeIfAbsent(user.getName(), k -> new ArrayList<>()).add(review);
    }

    /**
     * A get reviews method for testing the my reviews use case.
     * @param user a user of this program.
     * @return a list of MovieReview objects.
     */
    public List<MovieReview> getReviews(User user) {
        return userReviews.getOrDefault(user.getName(), new ArrayList<>());
    }

    @Override
    public void setCurrentUsername(String name) {
        this.currentUsername = name;
    }

    @Override
    public String getCurrentUsername() {
        return this.currentUsername;
    }

    @Override
    public ArrayList<UserWatchlist> getWatchlists(User user) {
        return user.getWatchlists();
    }

    @Override
    public boolean deleteWatchlist(User user, int ind) {

        return true;
    }

    @Override
    public boolean renameWatchlist(User user, int ind, String newName) {
        final UserWatchlist watchlist = (UserWatchlist) user.getWatchlists().get(ind);
        watchlist.changeListName(newName);
        return newName.equals(user.getWatchlists().get(ind).getListName());
    }

}
