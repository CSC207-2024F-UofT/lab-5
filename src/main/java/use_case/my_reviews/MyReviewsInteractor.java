package use_case.my_reviews;

import java.util.List;

import entity.MovieReview;
import entity.User;

/**
 * The interactor for the leave a review use case.
 */
public class MyReviewsInteractor implements MyReviewsInputBoundary {
    private final MyReviewsDataAccessInterface myReviewsDataAccessObject;
    private final MyReviewsOutputBoundary myReviewsPresenter;

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
        final List<MovieReview> reviews = myReviewsDataAccessObject.getReviews(user);
        // turn this into a piece of My_ReviewsOutputData
        final MyReviewsOutputData myReviewsOutputData = new MyReviewsOutputData(reviews);

        if (reviews.isEmpty()) {
            // No reviews found for the user
            myReviewsPresenter.prepareNoReviewsView("No reviews yet.");
        }
        else {
            // Pass the retrieved reviews to the presenter
            myReviewsPresenter.prepareMyReviewsView(myReviewsOutputData);
        }
    }

    /**
     * Goes back to the previous screen.
     */
    public void goBack() {
        myReviewsPresenter.goBack();
    }
}
