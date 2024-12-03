package interface_adapter.my_reviews;

import java.util.List;
import java.util.stream.Collectors;

import interface_adapter.ViewManagerModel;
import use_case.my_reviews.MyReviewsOutputData;
import use_case.my_reviews.MyReviewsOutputBoundary;

/**
 * The Presenter for the "my reviews" use case.
 */
public class MyReviewsPresenter implements MyReviewsOutputBoundary {
    private final MyReviewsViewModel viewModel;
    private final ViewManagerModel viewManagerModel;

    public MyReviewsPresenter(MyReviewsViewModel viewModel,
                              ViewManagerModel viewManagerModel) {
        this.viewModel = viewModel;
        this.viewManagerModel = viewManagerModel;
    }

    @Override
    public void prepareMyReviewsView(MyReviewsOutputData reviews) {
        // Format each review into a separate string
        final List<String> formattedReviews = reviews.getReviews().stream()
                .map(review -> {
                    final StringBuilder reviewString = new StringBuilder();
                    reviewString.append("- Title: ").append(review.getMovieTitle()).append("\n")
                            .append("  Date: ").append(review.getDate()).append("\n")
                            .append("  Stars: ").append(review.getStarRating()).append("\n");
                    if (review.getContent() != null) {
                        reviewString.append("  Review: ").append(review.getContent()).append("\n");
                    }
                    reviewString.append("-----------------------------------\n");
                    return reviewString.toString();
                })
                .collect(Collectors.toList());

        viewModel.setState(formattedReviews);
        viewModel.firePropertyChanged();

        this.viewManagerModel.setState(viewModel.getViewName());
        this.viewManagerModel.firePropertyChanged();
    }

    /**
     * go back from the "my reviews" screen.
     * */

    public void goBack() {
        viewManagerModel.setState("logged in");
        viewManagerModel.firePropertyChanged();

    }

    @Override
    public void prepareFailView(String msg) {
            viewManagerModel.setState("Error");
            viewManagerModel.firePropertyChanged();
        }


    @Override
    public void prepareNoReviewsView(String message) {
        viewModel.setState(null);
        viewModel.firePropertyChanged();
        this.viewManagerModel.setState(viewModel.getViewName());
        this.viewManagerModel.firePropertyChanged();
    }

}
