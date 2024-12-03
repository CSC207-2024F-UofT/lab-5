package interface_adapter.my_reviews;

import java.util.List;

import interface_adapter.ViewModel;

/**
 * The view model for the "my reviews" use case.
 */

public class MyReviewsViewModel extends ViewModel<List<String>> {
    private List<String> reviews;

    public MyReviewsViewModel() {
        super("My_ReviewsView");
    }

    public List<String> getReviews() {
        return reviews;
    }

    public void setReviews(List<String> reviews) {
        this.reviews = reviews;

    }

}
