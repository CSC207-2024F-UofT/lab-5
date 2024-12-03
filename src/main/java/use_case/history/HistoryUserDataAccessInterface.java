package use_case.history;

import entity.Translate;
import entity.User;

import java.util.List;
import java.util.Map;

/**
 * DAO for the History Use Case.
 */
public interface HistoryUserDataAccessInterface {

    /**
     * Get all translation history.
     * @param user a user.
     * @return a list of translates result.
     */
    List<Translate> getAllTranslationsByUser(User user);

    /**
     * Get the most recent translation history.
     * @param user a user.
     * @return a list of recent translates result.
     */
    Translate getMostRecentTranslation(User user);

    /**
     * Delete the selected translation history.
     * @param user a user
     * @param translate Translate
     */
    void deleteTranslationHistory(User user, Translate translate);

    /**
     * Delete all translation history.
     * @param user a user
     */
    void clearHistory(User user);
    
//    /**
//     * Get a specific translation history.
//     * @return a map from input text to translation output.
//     */
//    Map<String, String> getTranslationHistory();
}
