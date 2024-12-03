package use_case.movie;

/**
 * The output boundary for the Movie Use Case.
 */
public interface MovieOutputBoundary {

    /**
     * Prepares the success view for the Movie Use Case.
     * @param movieOutputData the output data of the movie
     */
    void prepareSuccessView(MovieOutputData movieOutputData);

    /**
     * Switches to the Home View.
     */
    void switchToHomeView();

    /**
     * Switches to the User Reviews View.
     */
    void switchToUserReviewsView();

    void switchToLeaveReviewView();

}
