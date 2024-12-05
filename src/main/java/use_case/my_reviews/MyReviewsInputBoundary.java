package use_case.my_reviews;

import entity.User;

/**
 * The input boundary for the " My Reviews " use case.
 */
public interface MyReviewsInputBoundary {

    /**
     * Find the reviews corresponding to this user.
     * @param user a user of this application.
      */
    void execute(User user);

    /**
     * Go back.
     */
    void goBack();
}
