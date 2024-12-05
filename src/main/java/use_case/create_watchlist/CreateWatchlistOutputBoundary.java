package use_case.create_watchlist;

import entity.User;

/**
 * The output boundary for the create watchlist use case.
 */
public interface CreateWatchlistOutputBoundary {
    /**
     * Prepare success view.
     * @param user the user of this program.
     */
    void prepareSuccessView(User user);

    /**
     * Prepare fail view.
     * @param errorMessage the error message that has occurred.
     */
    void prepareFailView(String errorMessage);
}
