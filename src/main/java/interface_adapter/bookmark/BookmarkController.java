package interface_adapter.bookmark;

import use_case.bookmark.BookmarkInputBoundary;
import use_case.bookmark.BookmarkInputData;

/**
 * Controller for the Bookmark Use Case.
 * Receives input from the UI and passes it to the use case interactor.
 */
public class BookmarkController {
    private final BookmarkInputBoundary bookmarkInputBoundary;

    /**
     * Constructs a BookmarkController with the given input boundary.
     *
     * @param bookmarkInputBoundary The use case interactor to handle bookmark operations.
     */
    public BookmarkController(BookmarkInputBoundary bookmarkInputBoundary) {
        this.bookmarkInputBoundary = bookmarkInputBoundary;
    }

    /**
     * Adds a bookmark for the given translation.
     *
     * @param username The username of the user adding the bookmark.
     * @param translationId The ID of the translation to be bookmarked.
     */
    public void addBookmark(String username, String translationId) {
        final BookmarkInputData inputData = new BookmarkInputData(username, translationId);
        bookmarkInputBoundary.addBookmark(inputData);
    }

    /**
     * Removes a bookmark for the given translation.
     *
     * @param username The username of the user removing the bookmark.
     * @param translationId The ID of the translation to be removed from bookmarks.
     */
    public void removeBookmark(String username, String translationId) {
        final BookmarkInputData inputData = new BookmarkInputData(username, translationId);
        bookmarkInputBoundary.removeBookmark(inputData);
    }
}
