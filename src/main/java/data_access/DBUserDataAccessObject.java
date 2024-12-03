package data_access;

import static com.mongodb.client.model.Filters.eq;

import java.util.*;

import com.mongodb.client.model.UpdateOptions;
import org.bson.Document;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import entity.*;
import use_case.add_to_watchlist.AddToWatchlistDataAccessInterface;

import static com.mongodb.client.model.Filters.eq;

import use_case.create_watchlist.CreateWatchlistDataAccessInterface;
import use_case.home.HomeUserDataAccessInterface;
import use_case.leave_a_review.LeaveReviewDataAccessInterface;
import use_case.login.LoginUserDataAccessInterface;
import use_case.logout.LogoutUserDataAccessInterface;
import use_case.my_reviews.MyReviewsDataAccessInterface;
import use_case.signup.SignupUserDataAccessInterface;
import use_case.survey1.Survey1UserDataAccessInterface;
import use_case.survey_second_page.SurveySecondPageDataAccessInterface;
import use_case.watchlist.WatchlistUserDataAccessInterface;
import use_case.watchlists.WatchlistsUserDataAccessInterface;
import use_case.watchlists.delete.DeleteWatchlistUserDataAccessInterface;
import use_case.watchlists.rename.RenameUserDataAccessInterface;

/**
 * The DAO for user data.
 */
public class DBUserDataAccessObject implements SignupUserDataAccessInterface,
        LoginUserDataAccessInterface, HomeUserDataAccessInterface, MyReviewsDataAccessInterface,
        LogoutUserDataAccessInterface, WatchlistsUserDataAccessInterface, WatchlistUserDataAccessInterface, LeaveReviewDataAccessInterface,
        Survey1UserDataAccessInterface, SurveySecondPageDataAccessInterface,
        CreateWatchlistDataAccessInterface, RenameUserDataAccessInterface, AddToWatchlistDataAccessInterface,
        DeleteWatchlistUserDataAccessInterface {

    private static final int SUCCESS_CODE = 200;
    private static final String CONTENT_TYPE_LABEL = "Content-Type";
    private static final String CONTENT_TYPE_JSON = "application/json";
    private static final String STATUS_CODE_LABEL = "status_code";
    private static final String USERNAME = "username";
    private static final String PASSWORD = "password";
    private static final String MESSAGE = "message";
    private static final String WATCHLIST = "watchlist";
    private static final String WATCHLIST_NAME = "watchlistName";
    private static final String MOVIES = "movies";
    private final CommonUserFactory userFactory;
    private final CommonUserWatchlistFactory watchlistFactory;
    private final CommonMovieFactory movieFactory;

    DataBaseConstructor database = new DataBaseConstructor();
    MongoCollection<Document> collection = database.GetCollection("Users");

    public DBUserDataAccessObject(CommonUserFactory userFactory) {
        this.userFactory = userFactory;
        this.watchlistFactory = new CommonUserWatchlistFactory();
        this.movieFactory = new CommonMovieFactory();
        // No need to do anything to reinitialize a user list! The data is the cloud that may be miles away.
    }

    @Override
    public User get(String username) {
        final Document userDocument = collection.find(eq("userId", username)).first();

        if (userDocument != null) {
            // Extract fields from the document
            final String name = userDocument.getString(USERNAME);
            final String password = userDocument.getString(PASSWORD);

            // Retrieve the watchlists
            final List<Watchlist> watchlists = new ArrayList<>();
            final List<Document> watchlistDocuments = userDocument.getList(WATCHLIST, Document.class);
            if (watchlistDocuments != null) {
                for (Document watchlistDoc : watchlistDocuments) {
                    final String watchlistName = watchlistDoc.getString(WATCHLIST_NAME);
                    // create watchlist
                    final UserWatchlist watchlist = watchlistFactory.create(watchlistName);
                    watchlists.add(watchlist);

                    final List<String> movies = watchlistDoc.getList(MOVIES, String.class);
                    if (!movies.isEmpty()) {
                        for (String movieName : movies) {
                            final Movie movie = movieFactory.create(movieName);
                            try {
                                watchlist.addMovie(movie);
                            }
                            catch (Exception e) {
                                System.out.println("Movie not saved: " + e.getMessage());
                            }
                        }
                    }
                }
            }

            // Retrieve the user's preferred genres
            final Map<String, Integer> preferredGenres = new HashMap<>();
            final List<Document> preferredGenresDocs = userDocument.getList("preferredGenres", Document.class);

            if (preferredGenresDocs != null) {
                for (Document genreDoc : preferredGenresDocs) {
                    final List<String> genreNames = genreDoc.getList("genreNames", String.class);
                    final List<Integer> genreScores = genreDoc.getList("weight", Integer.class);

                    for (int i = 0; i < genreNames.size(); i++) {
                        preferredGenres.put(genreNames.get(i), genreScores.get(i));
                    }
                }
            }
            else {
                // Initialize the preferred genres with default values
                final GenreMap genreMap = new GenreMap();
                for (String genre : genreMap.keySet()) {
                    preferredGenres.put(genre, 0);
                }
            }

            // Create and return the User object
            final User user = userFactory.create(name, password);
            user.setWatchlists(watchlists);
            user.setPreferredGenres(preferredGenres);

            return user;
        }
        else {
            // Handle case where user is not found
            throw new RuntimeException("User with username '" + username + "' not found.");
        }
    }

    @Override
    public void setCurrentUsername(String name) {
        // this isn't implemented for the lab
    }

    @Override
    public boolean existsByName(String username) {
        final MongoCollection<Document> collection = DataBaseConstructor.GetCollection("Users");
        final FindIterable<Document> findIterable = collection.find(eq("userId", username));

        return findIterable.first() != null;
    }

    @Override
    public boolean movieExists(String movie) {
        return false;
    }

    @Override
    public void save(User user) {
        final Document newAccount = new Document("userId", user.getName())
                .append("username", user.getName())
                .append("password", user.getPassword());
        collection.insertOne(newAccount);

    }

    @Override
    public boolean saveWatchlist(User user, UserWatchlist watchlist) {
        boolean success = false;
        try {
            // Create a document representing the review
            Document watchlistDoc = new Document()
                    .append(WATCHLIST_NAME, watchlist.getListName())
                    .append(MOVIES, watchlist.getMovies());

            collection.updateOne(
                    new Document("userId", user.getName()),
                    new Document("$push", new Document(WATCHLIST, watchlistDoc))
            );

            success = true;
        } catch (Exception e) {
            System.err.println("Error adding watchlist to user: " + e.getMessage());
        }
        return success;
    }

    @Override
    public boolean deleteWatchlist(User user, int ind) {
        boolean success = false;
        try {
            collection.updateOne(
                    new Document("userId", user.getName()),
                    new Document("$unset", new Document(WATCHLIST + "." + ind, ind))
            );
            collection.updateOne(
                    new Document("userId", user.getName()),
                    new Document("$pull", new Document(WATCHLIST, null))
            );

            success = true;
        } catch (Exception e) {
            System.err.println("Error adding watchlist to user: " + e.getMessage());
        }
        return success;
    }

    @Override
    public boolean renameWatchlist(User user, int ind, String newName) {
        boolean success = false;
        final String path = WATCHLIST + "." + ind + "." + "watchlistName";
        try {
            collection.updateOne(
                    new Document("userId", user.getName()),
                    new Document("$set", new Document(path, newName))
            );

            success = true;
        } catch (Exception e) {
            System.err.println("Error adding watchlist to user: " + e.getMessage());
        }
        return success;
    }



    /**
     * Saves the preferred genres for a user.
     *
     * @param user the user whose preferred genres are to be saved
     * @param preferredGenres a map of genres and their corresponding preference levels
     * @return true if the preferred genres were saved successfully, false otherwise
     */
    @Override
    public boolean savePreferredGenres(User user, Map<String, Integer> preferredGenres) {
        boolean success = false;
        try {
            // Create a document representing the user's preferred genres
            final GenreMap genreMap = new GenreMap();
            final List<String> genreNames = Arrays.stream(genreMap.keySet()).toList();

            final List<Object> weight = new ArrayList<>();
            for (Integer score : preferredGenres.values()) {
                weight.add(score);
            }

            final Document preferredGenresDoc = new Document()
                    .append("genreNames", genreNames)
                    .append("weight", weight);

            collection.updateOne(
                    new Document("userId", user.getName()),
                    new Document("$push", new Document("preferredGenres", preferredGenresDoc))

            );
            success = true;
        }
        catch (Exception e) {
            System.out.println("Error adding preferred genres: " + e.getMessage());
        }
        System.out.println("Preferred genres saved successfully!");
        return success;
    }

    @Override
    public void saveToWatchlist(User user, Watchlist watchlist, Movie movie) {

        try {
            // Find the user's document in the collection
            final Document userDoc = collection.find(new Document("userId", user.getName())).first();

            if (userDoc != null) {
                // Retrieve the user's watchlists
                final List<Document> userWatchlists = (List<Document>) userDoc.get(WATCHLIST);

                // Find the specific watchlist by name
                Document targetWatchlist = null;
                for (Document doc : userWatchlists) {
                    if (doc.getString(WATCHLIST_NAME).equals(watchlist.getListName())) {
                        targetWatchlist = doc;
                        break;
                    }
                }

                if (targetWatchlist != null) {
                    // Update the watchlist by adding the new movie
                    collection.updateOne(
                            new Document("userId", user.getName())
                                    .append("watchlists.watchlistName", watchlist.getListName()),
                            new Document("$push", new Document("watchlists.$.movies", movie.getTitle()))
                    );
                    System.out.println("Movie added to watchlist successfully!");
                }
                else {
                    System.err.println("Watchlist not found.");
                }
            }
            else {
                System.err.println("User not found.");
            }
        }
        catch (Exception e) {
            System.err.println("Error adding movie to watchlist: " + e.getMessage());
        }
    }

    @Override
    public ArrayList<Watchlist> getWatchlists(User user) {
        // Initialize the factory to create watchlist objects
        final CommonUserWatchlistFactory watchlistFactory = new CommonUserWatchlistFactory();

        // Prepare the list to hold the user's watchlists
        final ArrayList<Watchlist> watchlists = new ArrayList<>();

        // Query the "Users" collection to find the user and their reviews
        final Document userDoc = collection.find(new Document("userId", user.getName())).first();

        if (userDoc != null) {
            // Extract the user's reviews (assuming reviews are stored in a sub-document or array)
            final List<Document> rawWatchlists = (List<Document>) userDoc.get("watchlists");

            if (rawWatchlists != null) {
                // Iterate over each review and transform it into a MovieReview object
                for (Document watchlistDoc : rawWatchlists) {
                    final String watchlistName = watchlistDoc.getString("watchlistName");
                    final List<Movie> movies = watchlistDoc.getList("movies", Movie.class);

                    // Use the factory to create the Watchlist
                    final Watchlist watchlist;
                    watchlist = watchlistFactory.create(watchlistName);

                    if (!movies.isEmpty()) {
                        for (Movie movie : movies) {
                            try {
                                watchlist.addMovie(movie);
                            } catch (Exception e) {
                                System.err.println("Error adding movie to watchlist: " + e.getMessage());
                            }
                        }
                    }

                    // Add the review to the list
                    watchlists.add(watchlist);
                }
            }
        }
        // Return the list of watchlists
        return watchlists;
    }

    /**
     * Leave a review for this user in the online DB.
     * @param review the review object to add to this user.
     * @return
     */
    public boolean leaveReview(MovieReview review) {
        final Document reviewDoc = new Document()
                    .append("movieTitle", review.getMovieTitle())
                    .append("date", review.getDate())
                    .append("starRating", review.getStarRating());

        if (review.getContent() != null) {
            reviewDoc.append("writtenReview", review.getContent());
        }

        collection.updateOne(
                    new Document("userId", review.getUserID()),
                    new Document("$push", new Document("reviews", reviewDoc))
        );

        return true;
    }


    /**
     * Retrieve the reviews for this user.
     * @param user a user of this program.
     * @return
     */

    public List<MovieReview> getReviews(User user) {

        final CommonMovieReviewFactory reviewFactory = new CommonMovieReviewFactory();

        final List<MovieReview> reviews = new ArrayList<>();

        final Document userDoc = collection.find(new Document("userId", user.getName())).first();

        if (userDoc != null) {
            final List<Document> rawReviews = (List<Document>) userDoc.get("reviews");

            if (rawReviews != null) {
                for (Document reviewDoc : rawReviews) {
                    final String username = userDoc.getString("userId");
                    final Date date = reviewDoc.getDate("date");
                    final Double starRating = reviewDoc.getDouble("starRating");
                    final String writtenReview = reviewDoc.getString("writtenReview");
                    final String movieTitle = reviewDoc.getString("movieTitle");

                    final MovieReview review;
                    if (writtenReview != null) {
                        review = reviewFactory.create(username, date, starRating, writtenReview, movieTitle);
                    } else {
                        review = reviewFactory.create(username, date, starRating, movieTitle);
                    }
                    reviews.add(review);
                }
            }
        }

        return reviews;
    }

    @Override
    public String getCurrentUsername() {
        return null;
    }
}
