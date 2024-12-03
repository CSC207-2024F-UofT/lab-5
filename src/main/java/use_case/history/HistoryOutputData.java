package use_case.history;

import entity.User;

import java.util.HashMap;
import java.util.Map;

/**
 * Output Data for the History Use Case.
 */
public class HistoryOutputData {
    private final User user;
    private final Map<String, String> history;
    private final boolean useCaseFailed;

    public HistoryOutputData(User user, Map<String, String> history, boolean useCaseFailed) {
        this.user = user;
        this.history = history;
        this.useCaseFailed = useCaseFailed;
    }

    public User getUser() {
        return user;
    }

    public Map<String, String> getHistory() {
        return history;
    }

//    public Map<String, String> setHistory(Map<String, String> history) {
//        this.history = history;
//    }

    public boolean isUseCaseFailed() {
        return useCaseFailed;
    }

}
