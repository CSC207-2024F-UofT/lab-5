package use_case.leave_a_review;

import entity.MovieReview;

/**
 * Access interface for the leave a review use case.
 */
public interface LeaveReviewDataAccessInterface {
     /**
      * Leaves a review in our DB associated with said user.
      * @param review the review that is being left.
      * @return if the review was successfully received.
      */
     boolean leaveReview(MovieReview review);
  }
