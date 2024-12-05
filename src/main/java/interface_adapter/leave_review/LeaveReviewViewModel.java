package interface_adapter.leave_review;

import interface_adapter.ViewModel;

/**
 * The view model for the leave a review use case.
 */

public class LeaveReviewViewModel extends ViewModel<LeaveReviewState> {
    private boolean success;

    public LeaveReviewViewModel() {
        super("LeaveReviewsView");
        setState(new LeaveReviewState());
    }

    public String getUsername() {
        return this.getState().getUsername();
    }

    public String getMovieName() {
        return this.getState().getMovieName();
    }

    /**
     * Set the state of the view model to true to indicate success of leaving a review.
     * @param  success1 was the review was submitted successfully or not.
     */

    public void success(boolean success1) {
        this.success = success1;
        firePropertyChanged();
    }
    /**
     * Set the state of the view model to false, indicate failure of leaving a review.
     */

    public boolean isSuccess() {
        return success;
    }
}
