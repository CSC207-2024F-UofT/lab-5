package data_access;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import entity.book.Book;
import entity.book.BookFactory;
import entity.Listing;
import entity.user.User;
import entity.user.UserFactory;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import use_case.wishlist.add_to_wishlist.AddToWishlistUserDataAccessInterface;
import use_case.change_password.ChangePasswordUserDataAccessInterface;
import use_case.login.LoginUserDataAccessInterface;
import use_case.logout.LogoutUserDataAccessInterface;
import use_case.wishlist.remove_from_wishlist.RemoveFromWishlistUserDataAccessInterface;
import use_case.sell.SellUserDataAccessInterface;
import use_case.signup.SignupUserDataAccessInterface;
import use_case.update_listings.UpdateListingsUserDataAccessInterface;
import use_case.wishlist.view_wishlist.ViewWishlistUserDataAccessInterface;

/**
 * DAO for user data using Firebase.
 */
public class FirebaseUserDataAccessObject implements SignupUserDataAccessInterface,
        LoginUserDataAccessInterface,
        ChangePasswordUserDataAccessInterface,
        LogoutUserDataAccessInterface,
        SellUserDataAccessInterface,
        RemoveFromWishlistUserDataAccessInterface,
        AddToWishlistUserDataAccessInterface,
        ViewWishlistUserDataAccessInterface,
        UpdateListingsUserDataAccessInterface {

    private static final int SUCCESS_CODE = 200;
    private static final String CONTENT_TYPE_LABEL = "Content-Type";
    private static final String CONTENT_TYPE_JSON = "application/json";

    private final UserFactory userFactory;
    private final OkHttpClient httpClient;
    private final String firebaseBaseUrl;
    private BookFactory bookFactory;

    /**
     * Constructor for FirebaseUserDataAccessObject.
     *
     * @param userFactory    Factory for creating User objects.
     * @param firebaseBaseUrl Base URL for the Firebase database.
     */
    public FirebaseUserDataAccessObject(final UserFactory userFactory, final BookFactory bookFactory, final String firebaseBaseUrl) {
        this.userFactory = userFactory;
        this.bookFactory = bookFactory;
        this.firebaseBaseUrl = firebaseBaseUrl;
        this.httpClient = new OkHttpClient();
    }

    @Override
    public User get(final String username) {
        String url = firebaseBaseUrl + "/users/" + username + ".json";
        Request request = new Request.Builder()
                .url(url)
                .get()
                .addHeader(CONTENT_TYPE_LABEL, CONTENT_TYPE_JSON)
                .build();

        try (Response response = httpClient.newCall(request).execute()) {
            if (response.isSuccessful()) {
                JSONObject userJson = new JSONObject(response.body().string());
                String name = userJson.getString("username");
                String password = userJson.getString("password");
                return userFactory.create(name, password);
            }
        }
        catch (IOException | JSONException e) {
            throw new RuntimeException("Error fetching user: " + e.getMessage(), e);
        }

        throw new RuntimeException("User not found");
    }

    @Override
    public void setCurrentUsername(final String name) {
        // This might involve storing the username in a local variable or shared preference if needed.
    }

    @Override
    public boolean existsByName(final String username) {
        String url = firebaseBaseUrl + "/users/" + username + ".json";
        Request request = new Request.Builder()
                .url(url)
                .get()
                .addHeader(CONTENT_TYPE_LABEL, CONTENT_TYPE_JSON)
                .build();

        try (Response response = httpClient.newCall(request).execute()) {
            return response.isSuccessful() && response.body() != null && !response.body().string().equals("null");
        } catch (IOException e) {
            throw new RuntimeException("Error checking user existence: " + e.getMessage(), e);
        }
    }

    @Override
    public void save(final User user) {
        final String url = firebaseBaseUrl + "/users/" + user.getName() + ".json";
        final JSONObject userJson = new JSONObject();

        try {
            userJson.put("username", user.getName());
            userJson.put("password", user.getPassword());
            final List<Listing> wishlist = user.getWishlist();
            final JSONArray jsonArray = new JSONArray();

            for (Listing listing : wishlist) {
                final JSONObject listingJson = new JSONObject();
                try {
                    listingJson.put("bookID", listing.getBook().getBookId());
                    listingJson.put("price", listing.getPrice());
                    jsonArray.put(listingJson);
                }
                catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            userJson.put("wishlist", jsonArray);

            final RequestBody body = RequestBody.create(userJson.toString(), MediaType.parse(CONTENT_TYPE_JSON));
            final Request request = new Request.Builder()
                    .url(url)
                    .put(body)
                    .addHeader(CONTENT_TYPE_LABEL, CONTENT_TYPE_JSON)
                    .build();

            try (Response response = httpClient.newCall(request).execute()) {
                if (!response.isSuccessful()) {
                    throw new RuntimeException("Failed to save user: " + response.message());
                }
            }
        }
        catch (JSONException | IOException e) {
            throw new RuntimeException("Error saving user: " + e.getMessage(), e);
        }
    }

    @Override
    public void changePassword(final User user) {
        save(user);
    }

    @Override
    public void removeFromWishlist(User user, Listing listing) {
        final String username = user.getName();

        // Construct the URL to get the user's wishlist from Firebase
        final String url = firebaseBaseUrl + "/users/" + username + "/wishlist.json";

        // Create a GET request to fetch the current wishlist of the user
        final Request request = new Request.Builder()
                .url(url)
                .get()
                .addHeader(CONTENT_TYPE_LABEL, CONTENT_TYPE_JSON)
                .build();

        try (Response response = httpClient.newCall(request).execute()) {
            if (response.isSuccessful() && response.body() != null) {
                final String responseBody = response.body().string();
                if ("null".equals(responseBody) || responseBody.isEmpty()) {
                    System.out.println("Wishlist is empty or not found.");
                    return;
                }

                final JSONObject wishlistJson = new JSONObject(responseBody);

                final Iterator<String> keys = wishlistJson.keys();
                String listingToRemoveKey = null;

                while (keys.hasNext()) {
                    final String key = keys.next();
                    final JSONObject jsonListing = wishlistJson.getJSONObject(key);
                    final String bookID = jsonListing.getString("bookID");
                    final String price = jsonListing.getString("price");
                    final String seller = jsonListing.getString("seller");
                    final boolean isAvailable = jsonListing.getBoolean("isAvailable");

                    final Listing currentListing = new Listing(
                            bookID, bookFactory.create(bookID), price, seller, isAvailable);

                    // Use the equals method to verify identity
                    if (listing.equals(currentListing)) {
                        listingToRemoveKey = key;
                        break;
                    }
                }

                // If the listing was found, remove it
                if (listingToRemoveKey != null) {
                    // Construct the URL to remove the listing from the wishlist
                    final String removeUrl = firebaseBaseUrl + "/users/" + username + "/wishlist/" + listingToRemoveKey
                            + ".json";

                    // Create a DELETE request to remove the listing
                    final Request deleteRequest = new Request.Builder()
                            .url(removeUrl)
                            .delete()
                            .addHeader(CONTENT_TYPE_LABEL, CONTENT_TYPE_JSON)
                            .build();

                    try (Response deleteResponse = httpClient.newCall(deleteRequest).execute()) {
                        if (deleteResponse.isSuccessful()) {
                            System.out.println("Listing successfully removed from wishlist.");
                        }
                        else {
                            System.err.println("Failed to remove listing from wishlist: " + deleteResponse.message());
                        }
                    }
                }
                else {
                    System.out.println("Listing not found in wishlist.");
                }
            }
            else {
                System.err.println("Failed to fetch wishlist: " + response.message());
            }
        }
        catch (IOException | JSONException exception) {
            exception.printStackTrace();
            System.err.println("Error removing listing from wishlist: " + exception.getMessage());
        }
    }

    @Override
    public void addToWishlist(User user, Listing listing) {
        final String url = firebaseBaseUrl + "/users/" + user.getName() + "/wishlist.json";
        final JSONObject listingJson = new JSONObject();
        try {
            // Populate listing JSON
            listingJson.put("bookID", listing.getBook().getBookId());
            listingJson.put("price", listing.getPrice());
            listingJson.put("seller", listing.getSeller());
            listingJson.put("isAvailable", true);

            // Create request
            final RequestBody body = RequestBody.create(listingJson.toString(), MediaType.parse(CONTENT_TYPE_JSON));
            final Request request = new Request.Builder()
                    .url(url)
                    .post(body)
                    .addHeader(CONTENT_TYPE_LABEL, CONTENT_TYPE_JSON)
                    .build();

            // Execute request
            try (Response response = httpClient.newCall(request).execute()) {
                if (!response.isSuccessful()) {
                    throw new RuntimeException("Failed to add listing to wishlist: " + response.message());
                }
                else {
                    System.out.println("Successfully added listing to wishlist.");
                }
            }
        }
        catch (JSONException | IOException e) {
            throw new RuntimeException("Error adding listing to wishlist: " + e.getMessage(), e);
        }
    }

    @Override
    public String getCurrentUsername() {
        return null;
    }

    @Override
    public List<Listing> getWishlist(User user) {
        // Assuming current username is stored somehow (e.g., in a member variable or method)
        final String username = user.getName();

        // Construct the URL to fetch the wishlist for the current user
        final String url = firebaseBaseUrl + "/users/" + username + "/wishlist.json";

        // Create a GET request to fetch the wishlist data
        final Request request = new Request.Builder()
                .url(url)
                .get()
                .addHeader(CONTENT_TYPE_LABEL, CONTENT_TYPE_JSON)
                .build();

        try (Response response = httpClient.newCall(request).execute()) {
            if (response.isSuccessful() && response.body() != null) {
                final String responseBody = response.body().string();
                if ("null".equals(responseBody)) {
                    return List.of();
                }

                final JSONObject wishlistJson = new JSONObject(responseBody);

                // Convert the JSON into a List of Listing objects
                final List<Listing> wishlist = new ArrayList<>();
                for (String key : wishlistJson.keySet()) {
                    final JSONObject listingJson = wishlistJson.getJSONObject(key);

                    // Extract the details for each listing
                    final String bookId = listingJson.getString("bookID");
                    final String price = listingJson.getString("price");
                    final String seller = listingJson.getString("seller");
                    final Book book = bookFactory.create(bookId);
                    final boolean isAvailable = listingJson.getBoolean("isAvailable");

                    // Create a Listing object (you need to have a proper constructor for Listing)
                    final Listing listing = new Listing(bookId, book, price, seller, isAvailable);
                    wishlist.add(listing);
                }

                return wishlist;
            }
            else {
                throw new RuntimeException("Failed to fetch wishlist: " + response.message());
            }
        }
        catch (IOException | JSONException e) {
            throw new RuntimeException("Error fetching wishlist: " + e.getMessage(), e);
        }
    }
}
