package use_case.show_Notes;

import java.util.List;

/**
 * DAO for show Notes usecase.
 */

public interface ShowNotesDataAccessInterface {
    /**
     * Gets the topics of the Notes in the current club.
     * @return list of topic names
     */
    List<String> getNotesTopics();

    /**
     * Sets the current Note topic in the current club.
     * @param currentNote the Note topic
     */
    void setCurrentNote(String currentNote);
}
