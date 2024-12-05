package interface_adapter.leave_review;

import interface_adapter.ViewManagerModel;
import use_case.leave_a_review.LeaveReviewOutputBoundary;

/**
 * The presenter for the leave a review use case.
 */

public class LeaveReviewPresenter implements LeaveReviewOutputBoundary {

    private final LeaveReviewViewModel viewModel;
    private final ViewManagerModel viewManagerModel;

    public LeaveReviewPresenter(LeaveReviewViewModel viewModel, ViewManagerModel viewManagerModel) {
        this.viewModel = viewModel;
        this.viewManagerModel = viewManagerModel;
    }
    /**
     * Redirects the user to the home screen.
     */

    public void goHome() {
        viewManagerModel.setState("logged in");
        viewManagerModel.firePropertyChanged();
    }
    /**
     * Redirects the user to the "IwatchedThis" screen.
     */

    public void goBack() {
        viewManagerModel.setState("movie");
        viewManagerModel.firePropertyChanged();
    }
    /**
     * Triggers the leve a review success notification.
     */

    public void success() {
        viewModel.success(true);
        viewModel.firePropertyChanged();
        viewManagerModel.setState("Leave a review success");
        viewManagerModel.firePropertyChanged();
    }

    /**
     * Triggers the leave a review failure notification.
     */
    public void failure() {
        viewModel.success(false);
        viewModel.firePropertyChanged();
        viewManagerModel.setState("Leave a review failure");
        viewManagerModel.firePropertyChanged();
    }
}
