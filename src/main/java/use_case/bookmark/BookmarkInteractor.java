package use_case.bookmark;

import entity.Bookmark;
import entity.BookmarkFactory;

/**
 * The Bookmark Interactor.
 * Implements the use case logic for adding and removing bookmarks.
 */
public class BookmarkInteractor implements BookmarkInputBoundary {
    private final BookmarkUserDataAccessInterface bookmarkDataAccess;
    private final BookmarkOutputBoundary bookmarkPresenter;
    private final BookmarkFactory bookmarkFactory;

    /**
     * Constructs a new BookmarkInteractor.
     *
     * @param bookmarkDataAccess The data access interface for bookmark operations.
     * @param bookmarkPresenter The output boundary for presenting bookmark results.
     * @param bookmarkFactory The factory for creating Bookmark entities.
     */
    public BookmarkInteractor(BookmarkUserDataAccessInterface bookmarkDataAccess,
                              BookmarkOutputBoundary bookmarkPresenter,
                              BookmarkFactory bookmarkFactory) {
        this.bookmarkDataAccess = bookmarkDataAccess;
        this.bookmarkPresenter = bookmarkPresenter;
        this.bookmarkFactory = bookmarkFactory;
    }

    /**
     * Adds a bookmark for a specific translation.
     *
     * @param inputData The input data containing information about the user and the translation to be bookmarked.
     */
    @Override
    public void addBookmark(BookmarkInputData inputData) {
        final Bookmark bookmark = bookmarkFactory.create(inputData.getUsername(), inputData.getTranslationId());
        bookmarkDataAccess.addBookmark(bookmark);
        bookmarkPresenter.prepareSuccessView(new BookmarkOutputData(inputData.getUsername(),
                inputData.getTranslationId(), "Bookmark added successfully"));
    }

    /**
     * Removes a bookmark for a specific translation.
     *
     * @param inputData The input data containing information about the user and the translation to be removed.
     */
    @Override
    public void removeBookmark(BookmarkInputData inputData) {
        final Bookmark bookmark = bookmarkFactory.create(inputData.getUsername(), inputData.getTranslationId());
        bookmarkDataAccess.removeBookmark(bookmark);
        bookmarkPresenter.prepareSuccessView(new BookmarkOutputData(inputData.getUsername(),
                inputData.getTranslationId(), "Bookmark removed successfully"));
    }
}
