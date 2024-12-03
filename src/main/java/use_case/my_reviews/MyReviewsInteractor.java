package use_case.my_reviews;

import entity.MovieReview;
import entity.User;

import java.util.List;

public class MyReviewsInteractor implements MyReviewsInputBoundary {
    private final MyReviewsDataAccessInterface myReviewsDataAccessObject; // Handles data access
    private final MyReviewsOutputBoundary myReviewsPresenter; // Handles presenting the output

    public MyReviewsInteractor(MyReviewsDataAccessInterface myReviewsDataAccessObject,
                               MyReviewsOutputBoundary myReviewsPresenter) {
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
        // turn this into a piece of My_ReviewsOutputData
        final MyReviewsOutputData my_ReviewsOutputData = new MyReviewsOutputData(reviews);

        if (reviews.isEmpty()) {
            // No reviews found for the user
            myReviewsPresenter.prepareNoReviewsView("No reviews yet.");
        } else {
            // Pass the retrieved reviews to the presenter
            myReviewsPresenter.prepareMyReviewsView(my_ReviewsOutputData);
        }
    }
    // implement the "go back" use case on the "my_ReviewsView" page
    public void goBack(){
        myReviewsPresenter.goBack();
    }
}
