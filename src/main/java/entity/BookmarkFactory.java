package entity;

/**
 * Factory for creating Bookmark objects.
 */
public class BookmarkFactory {

    /**
     * Creates a new Bookmark object.
     *
     * @param username The username of the user who owns the bookmark.
     * @param translationId The ID of the translation to be bookmarked.
     * @return A new Bookmark object.
     */
    public Bookmark create(String username, String translationId) {
        return new Bookmark(username, translationId);
    }
}
