package use_case.leave_a_review;

/**
 * The leave a review output boundary for our leave a review use case.
 */
public interface LeaveReviewOutputBoundary {
    /**
     * Go back.
     */
    void goBack();

    /**
     * Upon success.
     */
    void success();

    /**
     * Upon failure.
     */
    void failure();

    void goHome();
}
