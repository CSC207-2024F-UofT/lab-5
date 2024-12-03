package use_case.my_reviews;

import java.util.List;

import entity.MovieReview;
import entity.User;

/**
 * The data access interface for the "My_Reviews" use case.
 */
public interface MyReviewsDataAccessInterface {
    /**
     * Returns an arrayList of MovieReview objects associated to this user.
     * @param user a user of this program.
     */
    List<MovieReview> getReviews(User user);
}
