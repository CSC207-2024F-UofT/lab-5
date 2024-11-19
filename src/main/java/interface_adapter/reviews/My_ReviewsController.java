package interface_adapter.reviews;

import entity.User;
import use_case.my_reviews.My_ReviewsInputBoundary;

public class My_ReviewsController {
    private final My_ReviewsInputBoundary interactor;

    public My_ReviewsController(My_ReviewsInputBoundary interactor) {
        this.interactor = interactor;
    }

    public void getReviews(User user) {
        interactor.execute(user);
    }
}
