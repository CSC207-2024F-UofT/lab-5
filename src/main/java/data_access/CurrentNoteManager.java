package data_access;

/**
 * Manager for the current Note.
 */
public interface CurrentNoteManager {
    /**
     * Sets the current Note the user wants to open.
     * @param currentNote the Note the user wants to open
     */
    void setCurrentNote(String currentNote);

    /**
     * Gets the current Note the user wants to open.
     * @return current Note.
     */
    String getCurrentNote();
}
