package use_case.movie;

/**
 * Input Boundary for actions which are related to movies.
 */
public interface MovieInputBoundary {

    /**
     * Executes the movie use case.
     * @param movieInputData the input data
     */
    void execute(MovieInputData movieInputData);

    /**
     * Switches to the Home View.
     */
    void switchToHomeView();

    void switchToLeaveReviewView();
}
