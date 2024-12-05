package interface_adapter.my_reviews;

import entity.User;
import use_case.my_reviews.MyReviewsInputBoundary;

/**
 * The controller for the "my reviews" use case.
 */
public class MyReviewsController {
    private final MyReviewsInputBoundary interactor;

    public MyReviewsController(MyReviewsInputBoundary interactor) {
        this.interactor = interactor;
    }

    /**
     * The get reviews method that calls the interactor to return the reviews of this specific user.
     * @param user the user who is currently logged in.
     */

    public void getReviews(User user) {
        interactor.execute(user);
    }

    /**
     * The go back use case that is triggered when the user wants to return to the previous screen.
     */
    public void goBack() {
        interactor.goBack();
    }
}
