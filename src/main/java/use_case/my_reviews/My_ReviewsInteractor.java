package use_case.my_reviews;

import entity.MovieReview;
import entity.User;

import java.security.PublicKey;
import java.util.List;

public class My_ReviewsInteractor implements My_ReviewsInputBoundary{
    private final My_ReviewsDataAccessInterface myReviewsDataAccessObject; // Handles data access
    private final My_ReviewsOutputBoundary myReviewsPresenter; // Handles presenting the output

    public My_ReviewsInteractor(My_ReviewsDataAccessInterface myReviewsDataAccessObject,
                               My_ReviewsOutputBoundary myReviewsPresenter) {
        this.myReviewsDataAccessObject = myReviewsDataAccessObject;
        this.myReviewsPresenter = myReviewsPresenter;
    }

    @Override
    public void execute(User user) {
        if (user == null) {
            throw new IllegalArgumentException("User cannot be null.");
        }

        // Retrieve the list of reviews for the user
        List<MovieReview> reviews = myReviewsDataAccessObject.getReviews(user);
        // turn this into a peice of My_ReviewsOutputDate
        final My_ReviewsOutputData my_ReviewsOutputData = new My_ReviewsOutputData(reviews);

        if (reviews.isEmpty()) {
            // No reviews found for the user
            myReviewsPresenter.prepareNoReviewsView("No reviews yet.");
        } else {
            // Pass the retrieved reviews to the presenter
            myReviewsPresenter.prepareMyReviewsView(my_ReviewsOutputData);
        }
    }
}
