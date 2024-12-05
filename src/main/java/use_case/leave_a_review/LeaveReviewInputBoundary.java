package use_case.leave_a_review;

/**
 * The input boundary for the leave a review use case. We include 2 overloaded execute methods
 * that must be implemented.
 */
public interface LeaveReviewInputBoundary {
    /**
     * This is the execute method that must be implemented by the leave a review interactor.
     * @param userID the userID, needed to fetch user information.
     * @param stars the star rating.
     * @param writtenReview the written component of this review.
     * @param movieTitle the title of this movie.
     */
    void execute(String userID, Double stars, String writtenReview, String movieTitle);

    /**
     * This is the execute method that must be implemented by the leave a review interactor, in the
     * case that there is no written review.
     * @param userID the userID, needed to fetch user information.
     * @param stars the star rating.
     * @param movieTitle the title of this movie.
     */
    void execute(String userID, Double stars, String movieTitle);
    /**
     * The interactor also must implement the goBack use case when the user wants to go back to the previous
     * screen from the leave a review screen.
     */

    void goBack();


    void goHome();
}
