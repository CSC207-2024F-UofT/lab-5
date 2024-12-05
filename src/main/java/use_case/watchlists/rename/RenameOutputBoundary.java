package use_case.watchlists.rename;

/**
 * The output boundary for the Rename Use Case.
 */
public interface RenameOutputBoundary {

    /**
     * Updates the watchlists view after the watchlist is renamed.
     */
    void execute();
}
