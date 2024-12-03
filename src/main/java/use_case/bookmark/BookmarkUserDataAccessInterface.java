package use_case.bookmark;

import java.util.List;

import entity.Bookmark;

/**
 * The interface for the Bookmark Data Access Object.
 * This interface provides methods to interact with the Bookmark data,
 * such as adding, removing, and retrieving bookmarks.
 */
public interface BookmarkUserDataAccessInterface {

    /**
     * Adds a bookmark to the system for a specific user.
     *
     * @param bookmark The bookmark to be added.
     */
    void addBookmark(Bookmark bookmark);

    /**
     * Removes a bookmark from the system for a specific user.
     *
     * @param bookmark The bookmark to be removed.
     */
    void removeBookmark(Bookmark bookmark);

    /**
     * Retrieves the list of bookmarks for a specific user.
     *
     * @param username The username of the user whose bookmarks are to be retrieved.
     * @return A list of bookmarks associated with the specified user.
     */
    List<Bookmark> getBookmarksByUser(String username);
}
