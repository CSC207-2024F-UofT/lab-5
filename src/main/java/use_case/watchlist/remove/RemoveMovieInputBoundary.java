package use_case.watchlist.remove;

/**
 * Input Boundary for actions which are related to watchlists.
 */
public interface RemoveMovieInputBoundary {
    /**
     * Executes the remove movie use case.
     * @param removeInputData the input data
     */
    void execute(RemoveMovieInputData removeInputData);
}
