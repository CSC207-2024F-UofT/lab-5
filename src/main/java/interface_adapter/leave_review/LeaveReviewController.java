package interface_adapter.leave_review;

import use_case.leave_a_review.LeaveReviewInputBoundary;

/**
 * The controller for the leave a review use case.
 */

public class LeaveReviewController {
    private final LeaveReviewInputBoundary leaveReviewInteractor;

    public LeaveReviewController(LeaveReviewInputBoundary leaveReviewInteractor) {
        this.leaveReviewInteractor = leaveReviewInteractor;
    }
    /**
     * The leaveReview use case.
     * @param userID The user of the program.
     * @param stars The star ratings.
     * @param writtenReview The written content.
     * @param movieTitle The title of the movie.
     */

    public void leaveReview(String userID, Double stars, String writtenReview, String movieTitle) {
        leaveReviewInteractor.execute(userID, stars, writtenReview, movieTitle);
    }

    /**
     * The leave a review use case, (no written review).
     * @param userID The user of the program.
     * @param stars The star ratings.
     * @param movieTitle The title of the movie.
     */
    public void leaveReview(String userID, Double stars, String movieTitle) {
        leaveReviewInteractor.execute(userID, stars, movieTitle);
    }

    /**
     * the goBack method that is triggered when a user hits the go back button
     * on the leave a review screen.
     */

    public void goBack() {
        leaveReviewInteractor.goBack();
    }

    public void goHome() {
        leaveReviewInteractor.goHome();
    }
}
