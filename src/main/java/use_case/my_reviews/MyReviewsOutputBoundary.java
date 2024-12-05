package use_case.my_reviews;

/**
 * The output boundary for the my reviews use case.
 */
public interface MyReviewsOutputBoundary {
    /**
     * Preapre the reviews for this user.
     * @param my_reviewsOutputData the movie review objects associated tot his user.
     */
    void prepareMyReviewsView(MyReviewsOutputData my_reviewsOutputData);

    /**
     * Go back.
     */
    void goBack();

    /**
     * Prepare the view for users without any reviews.
     * @param noReviewsMsg the msg shown if no reviews are there.
     */

    void prepareNoReviewsView(String noReviewsMsg);

    /**
     * Prepare the fail view if this use case fails.
     * @param errorMessage the error message given.
     */
    void prepareFailView(String errorMessage);
}
