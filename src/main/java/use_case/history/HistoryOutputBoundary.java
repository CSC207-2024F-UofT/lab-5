package use_case.history;

/**
 * The output boundary for the History Use Case.
 */
public interface HistoryOutputBoundary {

    /**
     * Prepares the success view for the History Use Case.
     * @param outputData the output data
     */
    void prepareSuccessView(HistoryOutputData outputData);

    /**
     * Prepares the failure view for the History Use Case.
     * @param errorMessage the explanation of the failure
     */
    void prepareFailView(String errorMessage);
}
