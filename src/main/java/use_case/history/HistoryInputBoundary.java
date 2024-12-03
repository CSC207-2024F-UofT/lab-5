package use_case.history;

/**
 * Input Boundary for actions which are related to History.
 */
public interface HistoryInputBoundary {

    /**
     * Executes the history case.
     * @param historyInputData the input data
     */
    void execute(HistoryInputData historyInputData);

    /**
     * Executes to go back to translate view.
     */
    void switchBackToTranslateView();

    /**
     * Executes to go to history view.
     */
    void switchToHistoryView();
}
