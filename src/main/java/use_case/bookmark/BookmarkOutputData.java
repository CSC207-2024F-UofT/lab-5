package use_case.bookmark;

/**
 * Output data for the Bookmark Use Case.
 */
public class BookmarkOutputData {
    private final String username;
    private final String translationId;
    private final String message;

    public BookmarkOutputData(String username, String translationId, String message) {
        this.username = username;
        this.translationId = translationId;
        this.message = message;
    }

    public String getUsername() {
        return username;
    }

    public String getTranslationId() {
        return translationId;
    }

    public String getMessage() {
        return message;
    }
}
