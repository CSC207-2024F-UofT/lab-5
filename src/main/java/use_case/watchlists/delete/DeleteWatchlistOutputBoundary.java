package use_case.watchlists.delete;

/**
 * The output boundary for the Rename Use Case.
 */
public interface DeleteWatchlistOutputBoundary {

    /**
     * Updates the watchlists view after the watchlist is renamed.
     */
    void execute();
}
