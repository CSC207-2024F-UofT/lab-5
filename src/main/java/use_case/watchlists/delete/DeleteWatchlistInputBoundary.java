package use_case.watchlists.delete;

/**
 * Input Boundary for actions which are related to watchlists.
 */
public interface DeleteWatchlistInputBoundary {
    /**
     * Executes the rename watchlist use case.
     * @param ranameInputData the input data
     */
    void execute(DeleteWatchlistInputData ranameInputData);
}
