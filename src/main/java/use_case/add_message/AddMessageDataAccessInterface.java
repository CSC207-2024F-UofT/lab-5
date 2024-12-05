package use_case.add_message;

import java.util.AbstractMap;
import java.util.List;

import data_access.CurrentClubManager;
import data_access.CurrentNoteManager;

/**
 * The interface of the DAO for the add message usecase.
 */
public interface AddMessageDataAccessInterface extends CurrentClubManager,
        CurrentNoteManager {
    /**
     * Saves the message of the current user in the current discussion that is in the current book club.
     * @param text text in the message
     * @param currentUsername  the username of the sender of the message
     */
    void saveMessage(String text, String currentUsername);

    /**
     * Gets all messages.
     * @return a list containing a simple entry of username and text.
     */
    List<AbstractMap.SimpleEntry<String, String>> getMessages();

}
