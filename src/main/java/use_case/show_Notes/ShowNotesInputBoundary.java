package use_case.show_Notes;

/**
 * Input boundary for actions related to showing discussion topics of the current book club.
 */
public interface ShowNotesInputBoundary {
    /**
     * Executes the show Notes use case.
     */
    void execute();

    /**
     * Switch to add message view.
     * @param Note the Note chosen by the user
     */
    void switchToAddMessageView(String Note);
}
