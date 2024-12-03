package use_case.bookmark;

/**
 * The Bookmark Use Case Input Boundary.
 * Defines the interface to execute bookmark actions.
 */
public interface BookmarkInputBoundary {
    /**
     * Adds a bookmark for a specific translation.
     *
     * @param inputData The input data containing information about the user and the translation to be bookmarked.
     */
    void addBookmark(BookmarkInputData inputData);

    /**
     * Removes a bookmark for a specific translation.
     *
     * @param inputData The input data containing information about the user and the translation to be removed.
     */
    void removeBookmark(BookmarkInputData inputData);
}
