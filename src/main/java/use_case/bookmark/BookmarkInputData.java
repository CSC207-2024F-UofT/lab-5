package use_case.bookmark;

/**
 * The input data for the Bookmark Use Case.
 * Contains the information required to perform a bookmark action.
 */
public class BookmarkInputData {
    private final String username;
    private final String translationId;

    /**
     * Constructs a new BookmarkInputData object.
     *
     * @param username The username of the user performing the action.
     * @param translationId The ID of the translation to be bookmarked or removed.
     */
    public BookmarkInputData(String username, String translationId) {
        this.username = username;
        this.translationId = translationId;
    }

    /**
     * Gets the username of the user performing the action.
     *
     * @return The username of the user.
     */
    public String getUsername() {
        return username;
    }

    /**
     * Gets the ID of the translation to be bookmarked or removed.
     *
     * @return The translation ID.
     */
    public String getTranslationId() {
        return translationId;
    }
}
