package use_case.add_to_watchlist;

import entity.User;

/**
 * The output boundary for our add to watch list use case.
 */
public interface AddToWatchlistOutputBoundary {
    /**
     * The view prepare success view method that the presenter must implement.
     * @param user the user of the program.
     */
    void prepareSuccessView(User user);

    /**
     * Fail view that must be implemented.
     * @param errorMessage the error that has occurred.
     */
    void prepareFailView(String errorMessage);
}
