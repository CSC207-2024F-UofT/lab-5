package use_case.my_reviews;

import java.util.List;

import entity.MovieReview;

/**
 * The format for the output data of the "My Reviews" use case.
 */
public class MyReviewsOutputData {
    private final List<MovieReview> reviews;

    public MyReviewsOutputData(List<MovieReview> reviews) {
        this.reviews = reviews;
    }

    public List<MovieReview> getReviews() {
        return reviews;
    }
}

