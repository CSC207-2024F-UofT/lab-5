package use_case.watchlists.rename;

import entity.User;

/**
 * Input Boundary for actions which are related to watchlists.
 */
public interface RenameInputBoundary {
    /**
     * Executes the rename watchlist use case.
     * @param ranameInputData the input data
     */
    void execute(RenameInputData ranameInputData);
}
