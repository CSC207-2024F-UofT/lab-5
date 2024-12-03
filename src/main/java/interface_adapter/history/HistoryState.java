package interface_adapter.history;

import java.util.Map;

/**
 * The State for the History View.
 */
public class HistoryState {
    private Map<String, String> history;
    private String historyError;

    public Map<String, String> getHistory() {
        return history;
    }

    public void setHistory(Map<String, String> history) {
        this.history = history;
    }

    public String getHistoryError() {
        return historyError;
    }

    public void setHistoryError(String historyError) {
        this.historyError = historyError;
    }
}
