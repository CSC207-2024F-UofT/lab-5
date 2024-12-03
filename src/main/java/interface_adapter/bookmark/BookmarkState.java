package interface_adapter.bookmark;

/**
 * Represents the state of the Bookmark feature.
 * Stores the username, translation ID, and any status messages.
 */
public class BookmarkState {
    private String username;
    private String translationId;
    private String statusMessage;

    /**
     * Constructs a BookmarkState with the given username, translation ID, and status message.
     *
     * @param username The username associated with the current state.
     * @param translationId The ID of the translation.
     * @param statusMessage The status message for the bookmark operation.
     */
    public BookmarkState(String username, String translationId, String statusMessage) {
        this.username = username;
        this.translationId = translationId;
        this.statusMessage = statusMessage;
    }

    public BookmarkState(BookmarkState copy) {
        this.username = copy.username;
        this.translationId = copy.translationId;
        this.statusMessage = copy.statusMessage;
    }

    // Default constructor.
    public BookmarkState() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getTranslationId() {
        return translationId;
    }

    public void setTranslationId(String translationId) {
        this.translationId = translationId;
    }

    public String getStatusMessage() {
        return statusMessage;
    }

    public void setStatusMessage(String statusMessage) {
        this.statusMessage = statusMessage;
    }
}
