package data_access;

import static com.mongodb.client.model.Filters.eq;

import java.util.*;

import org.bson.Document;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import entity.*;
import use_case.add_to_watchlist.AddToWatchlistDataAccessInterface;
import use_case.create_watchlist.CreateWatchlistDataAccessInterface;
import use_case.home.HomeUserDataAccessInterface;
import use_case.leave_a_review.LeaveReviewDataAccessInterface;
import use_case.login.LoginUserDataAccessInterface;
import use_case.logout.LogoutUserDataAccessInterface;
import use_case.my_reviews.MyReviewsDataAccessInterface;
import use_case.signup.SignupUserDataAccessInterface;
import use_case.survey_one.Survey1UserDataAccessInterface;
import use_case.survey_second_page.SurveySecondPageDataAccessInterface;
import use_case.watchlist.WatchlistUserDataAccessInterface;
import use_case.watchlist.remove.RemoveMovieUserDataAccessInterface;
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
        DeleteWatchlistUserDataAccessInterface, RemoveMovieUserDataAccessInterface {

    private static final String USER_ID = "userId";
    private static final String USERNAME = "username";
    private static final String PASSWORD = "password";
    private static final String WATCHLIST = "watchlist";
    private static final String WATCHLIST_NAME = "watchlistName";
    private static final String MOVIES = "movies";
    private static final String MOVIE_IDS = "movieIds";
    private static final String PW = "previouslyWatched";
    private static final String WATCHLIST_TO_USER = "Error adding watchlist to user: ";
    private static final String PUSH = "$push";
    private final CommonUserFactory userFactory;
    private final CommonUserWatchlistFactory watchlistFactory;
    private final APIMovieAccess apiMovieAccess = new APIMovieAccess();

    private final DataBaseConstructor database = new DataBaseConstructor();
    private final MongoCollection<Document> collection = database.getCollection("Users");

    public DBUserDataAccessObject(CommonUserFactory userFactory) {
        this.userFactory = userFactory;
        this.watchlistFactory = new CommonUserWatchlistFactory();
        // No need to do anything to reinitialize a user list! The data is the cloud that may be miles away.
    }

    @Override
    public User get(String username) {
        final Document userDocument = collection.find(eq(USER_ID, username)).first();

        if (userDocument != null) {
            // Extract fields from the document
            final String name = userDocument.getString(USERNAME);
            final String password = userDocument.getString(PASSWORD);

            final User user = userFactory.create(name, password);

            // Retrieve the watchlists
            final List<UserWatchlist> watchlists = new ArrayList<>();
            final List<Document> watchlistDocuments = userDocument.getList(WATCHLIST, Document.class);
            if (watchlistDocuments != null) {
                for (Document watchlistDoc : watchlistDocuments) {
                    final String watchlistName = watchlistDoc.getString(WATCHLIST_NAME);
                    // create watchlist
                    final UserWatchlist watchlist = watchlistFactory.create(watchlistName);

                    final List<String> movies = watchlistDoc.getList(MOVIES, String.class);

                    if (!movies.isEmpty()) {
                        final List<Integer> movieIds = watchlistDoc.getList(MOVIE_IDS, Integer.class);

                        for (int i = 0; i < movies.size(); i++) {
                            // final Integer id = Integer.parseInt(movieIds.get(i));
                            final Movie movie = apiMovieAccess.searchByID(movies.get(i), movieIds.get(i));
                            try {
                                watchlist.addMovie(movie);
                            }
                            catch (Exception exception) {
                                System.out.println("Movie not saved: " + exception.getMessage());
                            }
                        }
                    }
                    watchlists.add(watchlist);
                }
            }
            user.setWatchlists(watchlists);

            // Retrieve PWL
            final Document pwlDoc = userDocument.get(PW, Document.class);
            if (pwlDoc != null) {
                final String pwlName = pwlDoc.getString(WATCHLIST_NAME);
                final UserWatchlist pwl = watchlistFactory.create(pwlName);

                final List<String> pwlMovies = pwlDoc.getList(MOVIES, String.class);
                if (pwlMovies != null && !pwlMovies.isEmpty()) {
                    final List<Integer> pwlmovieIds = pwlDoc.getList(MOVIE_IDS, Integer.class);
                    for (int i = 0; i < pwlMovies.size(); i++) {
                        final Movie movie = apiMovieAccess.searchByID(pwlMovies.get(i), pwlmovieIds.get(i));
                        try {
                            pwl.addMovie(movie);
                        }
                        catch (Exception exception) {
                            System.out.println("Movie not added to PWL: " + exception.getMessage());
                        }
                    }
                }
                user.setPwl(pwl);
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
        final MongoCollection<Document> collection = DataBaseConstructor.getCollection("Users");
        final FindIterable<Document> findIterable = collection.find(eq(USER_ID, username));

        return findIterable.first() != null;
    }

    @Override
    public boolean movieExists(String movie) {
        return false;
    }

    @Override
    public void save(User user) {
        final Document newAccount = new Document(USER_ID, user.getName())
                .append("username", user.getName())
                .append("password", user.getPassword());
        collection.insertOne(newAccount);

    }

    @Override
    public boolean saveWatchlist(User user, UserWatchlist watchlist) {
        boolean success = false;
        try {
            final List<String> movieNames = new ArrayList<>();
            final List<Integer> movieIds = new ArrayList<>();
            for (Movie movie : watchlist.getMovies()) {
                movieNames.add(movie.getTitle());
                movieIds.add(movie.getMovieId());
            }

            // Create a document representing the review
            final Document watchlistDoc = new Document()
                    .append(WATCHLIST_NAME, watchlist.getListName())
                    .append(MOVIES, movieNames)
                    .append("movieIds", movieIds);

            collection.updateOne(
                    new Document(USER_ID, user.getName()),
                    new Document(PUSH, new Document(WATCHLIST, watchlistDoc))
            );

            success = true;
        }
        catch (Exception exception) {
            System.err.println(WATCHLIST_TO_USER + exception.getMessage());
        }
        return success;
    }

    @Override
    public boolean deleteWatchlist(User user, int ind) {
        boolean success = false;
        try {
            collection.updateOne(
                    new Document(USER_ID, user.getName()),
                    new Document("$unset", new Document(WATCHLIST + "." + ind, ind))
            );
            collection.updateOne(
                    new Document(USER_ID, user.getName()),
                    new Document("$pull", new Document(WATCHLIST, null))
            );

            success = true;
        }
        catch (Exception exception) {
            System.err.println(WATCHLIST_TO_USER + exception.getMessage());
        }
        return success;
    }

    @Override
    public boolean deleteFromWatchlist(User user, int watchlistInd, int ind) {
        boolean success = false;
        try {
            final String path1 = WATCHLIST + "." + watchlistInd + ".movies";
            final String path2 = WATCHLIST + "." + watchlistInd + ".movieIds";
            collection.updateOne(
                    new Document("userId", user.getName()),
                    new Document("$unset", new Document(path1  + "." + ind, ind))
            );
            collection.updateOne(
                    new Document("userId", user.getName()),
                    new Document("$pull", new Document(path1, null))
            );

            collection.updateOne(
                    new Document("userId", user.getName()),
                    new Document("$unset", new Document(path2 + "." + ind, ind))
            );
            collection.updateOne(
                    new Document("userId", user.getName()),
                    new Document("$pull", new Document(path2, null))
            );

            success = true;
        } catch (Exception e) {
            System.err.println("Error adding watchlist to user: " + e.getMessage());
        }
        return success;
    }

    @Override
    public boolean deleteFromPwl(User user, int ind) {
        boolean success = false;
        try {
            final String path1 = "previouslyWatched" + ".movies";
            final String path2 = "previouslyWatched" + ".movieIds";
            collection.updateOne(
                    new Document("userId", user.getName()),
                    new Document("$unset", new Document(path1 + "." + ind, ind))
            );
            collection.updateOne(
                    new Document("userId", user.getName()),
                    new Document("$pull", new Document(path1, null))
            );

            collection.updateOne(
                    new Document("userId", user.getName()),
                    new Document("$unset", new Document(path2 + "." + ind, ind))
            );
            collection.updateOne(
                    new Document("userId", user.getName()),
                    new Document("$pull", new Document(path2, null))
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
        final String path = WATCHLIST + "." + ind + "." + WATCHLIST_NAME;
        try {
            collection.updateOne(
                    new Document(USER_ID, user.getName()),
                    new Document("$set", new Document(path, newName))
            );

            success = true;
        }
        catch (Exception exception) {
            System.err.println(WATCHLIST_TO_USER + exception.getMessage());
        }
        return success;
    }

    @Override
    public void savePwl(User user) {
        try {
            final List<String> movieNames = new ArrayList<>();
            final List<Integer> movieIds = new ArrayList<>();
            for (Movie movie : user.getPwl().getMovies()) {
                movieNames.add(movie.getTitle());
                movieIds.add(movie.getMovieId());
            }
            // Create a document representing the review
            final Document pwlDoc = new Document()
                    .append(WATCHLIST_NAME, user.getPwl().getListName())
                    .append(MOVIES, movieNames)
                    .append(MOVIE_IDS, movieIds);

            collection.updateOne(
                    new Document(USER_ID, user.getName()),
                    new Document("$set", new Document(PW, pwlDoc))
            );
        }
        catch (Exception exception) {
            System.err.println("Error adding pwl to user: " + exception.getMessage());
        }
    }

    @Override
    public boolean saveToPwl(User user, Movie movie) {
        boolean success = false;
        try {
            // Find the user's document in the collection
            final Document userDoc = collection.find(new Document(USER_ID, user.getName())).first();

            if (userDoc != null) {
                final Document pwlDoc = userDoc.get(PW, Document.class);

                if (pwlDoc != null) {
                    // Update the watchlist by adding the new movie
                    collection.updateOne(
                            new Document(USER_ID, user.getName()),
                            new Document(PUSH, new Document(PW + "." + MOVIES, movie.getTitle()))
                    );
                    collection.updateOne(
                            new Document(USER_ID, user.getName()),
                            new Document(PUSH, new Document(PW + "." + MOVIE_IDS, movie.getMovieId()))
                    );
                    System.out.println("Movie added to watchlist successfully!");
                    success = true;
                }
                else {
                    // If the PWL doesn't exist, create it and add the movie
                    final Document newPwlDoc = new Document()
                            .append(WATCHLIST_NAME, "Previously Watched")
                            .append(MOVIES, Collections.singletonList(movie.getTitle()))
                            .append(MOVIE_IDS, List.of(movie.getMovieId()));
                    collection.updateOne(
                            new Document(USER_ID, user.getName()),
                            new Document("$set", new Document(PW, newPwlDoc))
                    );
                    System.out.println("Previously Watched List created and movie added!");
                }
            }
            else {
                System.err.println("User not found.");
            }
        }
        catch (Exception exception) {
            System.err.println("Error adding movie to previously watched list: " + exception.getMessage());
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

            final List<Object> weight = new ArrayList<>(preferredGenres.values());

            final Document preferredGenresDoc = new Document()
                    .append("genreNames", genreNames)
                    .append("weight", weight);

            collection.updateOne(
                    new Document(USER_ID, user.getName()),
                    new Document(PUSH, new Document("preferredGenres", preferredGenresDoc))

            );
            success = true;
        }
        catch (Exception exception) {
            System.out.println("Error adding preferred genres: " + exception.getMessage());
        }
        System.out.println("Preferred genres saved successfully!");
        return success;
    }

    @Override
    public boolean saveToWatchlist(User user, int ind, Movie movie) {
        boolean success = false;
        try {
            // Find the user's document in the collection
            final Document userDoc = collection.find(new Document(USER_ID, user.getName())).first();

            if (userDoc != null) {
                final String path1 = WATCHLIST + "." + ind + "." + MOVIES;
                final String path2 = WATCHLIST + "." + ind + "." + MOVIE_IDS;

                // Update the watchlist by adding the new movie
                collection.updateOne(
                        new Document(USER_ID, user.getName()),
                        new Document(PUSH, new Document(path1, movie.getTitle()))
                );
                collection.updateOne(
                        new Document(USER_ID, user.getName()),
                        new Document(PUSH, new Document(path2, movie.getMovieId()))
                );
                System.out.println("Movie added to watchlist successfully!");
                success = true;
            }
            else {
                System.err.println("User not found.");
            }
        }
        catch (Exception exception) {
            System.err.println("Error adding movie to watchlist: " + exception.getMessage());
        }
        return success;
    }

    @Override
    public ArrayList<UserWatchlist> getWatchlists(User user) {

        // Prepare the list to hold the user's watchlists
        final ArrayList<UserWatchlist> watchlists = new ArrayList<>();

        // Query the "Users" collection to find the user and their reviews
        final Document userDoc = collection.find(new Document(USER_ID, user.getName())).first();

        if (userDoc != null) {
            // Extract the user's reviews (assuming reviews are stored in a sub-document or array)
            final List<Document> rawWatchlists = (List<Document>) userDoc.get("watchlists");

            if (rawWatchlists != null) {
                // Iterate over each review and transform it into a MovieReview object
                for (Document watchlistDoc : rawWatchlists) {
                    final String watchlistName = watchlistDoc.getString(WATCHLIST_NAME);
                    final List<Movie> movies = watchlistDoc.getList(MOVIES, Movie.class);

                    // Use the factory to create the Watchlist
                    final UserWatchlist watchlist;
                    watchlist = watchlistFactory.create(watchlistName);

                    if (!movies.isEmpty()) {
                        for (Movie movie : movies) {
                            try {
                                watchlist.addMovie(movie);
                            }
                            catch (Exception exception) {
                                System.err.println("Error adding movie to watchlist: " + exception.getMessage());
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
     * @return success
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
                    new Document(USER_ID, review.getUserID()),
                    new Document(PUSH, new Document("reviews", reviewDoc))
        );

        return true;
    }

    /**
     * Retrieve the reviews for this user.
     * @param user a user of this program.
     * @return list
     */

    public List<MovieReview> getReviews(User user) {

        final CommonMovieReviewFactory reviewFactory = new CommonMovieReviewFactory();

        final List<MovieReview> reviews = new ArrayList<>();

        final Document userDoc = collection.find(new Document(USER_ID, user.getName())).first();

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
                    }
                    else {
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
