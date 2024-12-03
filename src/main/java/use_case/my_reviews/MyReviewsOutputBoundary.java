package use_case.my_reviews;

public interface MyReviewsOutputBoundary {

    void prepareMyReviewsView(MyReviewsOutputData my_reviewsOutputData);

    void goBack();

    void prepareNoReviewsView(String noReviewsMsg);

    void prepareFailView(String errorMessage);
}
