package use_case.my_reviews;
import entity.MovieReview;

import java.util.List;

public class My_ReviewsOutputData {
    private final List<MovieReview> reviews;

    public My_ReviewsOutputData(List<MovieReview> reviews) {
        this.reviews = reviews;
    }

    public List<MovieReview> getReviews() {
        return reviews;
    }
}

