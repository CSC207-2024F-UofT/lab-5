package use_case.history;

import data_access.InMemoryUserDataAccessObject;
import entity.Translate;
import entity.User;
import use_case.translator.TranslatorInputData;
import use_case.translator.TranslatorUserDataAccessInterface;

import java.util.List;

/**
 * The input data for the history UseCase.
 */
public class HistoryInputData {
    private final User user;

    public HistoryInputData(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public String getUsername() {
        return user.getName();
    }

//    /**
//     * Get all translations by user.
//     * @return a list of translations.
//     */
//    public List<Translate> getAllTranslationsByUser() {
//        final TranslatorUserDataAccessInterface inMemoryUserDataAccessObject = new InMemoryUserDataAccessObject();
//        return inMemoryUserDataAccessObject.getAllTranslationsByUser(user);
//    }
}
