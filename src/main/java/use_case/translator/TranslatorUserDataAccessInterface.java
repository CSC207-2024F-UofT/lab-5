package use_case.translator;

import java.util.List;

import entity.Translate;
import entity.User;

/**
 * DAO for handling translation operations.
 */
public interface TranslatorUserDataAccessInterface {

    /**
     * Saves a translation request.
     * @param translation the translation request to save
     */
    void saveTranslation(Translate translation);

    /**
     * Retrieves a translation by its ID.
     * @param text the unique identifier of the translation
     * @return the translation request associated with the given ID
     */
    Translate getTranslation(String text);

    /**
     * Deletes a translation by its ID.
     * @param text the unique identifier of the translation to delete
     */
    void deleteTranslation(String text);

    /**
     * Retrieves all translation requests made by a specific user.
     * @param user the user
     * @return a list of translations made by the user
     */
    List<Translate> getAllTranslationsByUser(User user);

    /**
     * Retrieves the most recent translation request for a specific user.
     * @param user the user
     * @return the most recent translation request made by the user
     */
    Translate getMostRecentTranslation(User user);

    /**
     * Checks if a translation exists by its text.
     * @param text the unique input text of the translation
     * @return true if the translation exists; false otherwise
     */
    boolean existsByText(String text);

    /**
     * Updates an existing translation request.
     * @param user the user using the translator
     * @param updatedTranslation the updated translation details
     */
    void updateTranslation(User user, Translate updatedTranslation);
}
