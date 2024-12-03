package entity;

/**
 * Represents a Bookmark for a translation.
 */
public class Bookmark {
    private final String username;
    private final String translationId;

    /**
     * Constructs a Bookmark object.
     *
     * @param username The username of the user who owns the bookmark.
     * @param translationId The ID of the translation being bookmarked.
     */
    public Bookmark(String username, String translationId) {
        this.username = username;
        this.translationId = translationId;
    }

    /**
     * Gets the username of the user who owns the bookmark.
     *
     * @return The username.
     */
    public String getUsername() {
        return username;
    }

    /**
     * Gets the ID of the translation being bookmarked.
     *
     * @return The translation ID.
     */
    public String getTranslationId() {
        return translationId;
    }
}
