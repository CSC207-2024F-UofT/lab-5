package interface_adapter.bookmark;

import use_case.bookmark.BookmarkOutputBoundary;
import use_case.bookmark.BookmarkOutputData;

/**
 * The Presenter for the Bookmark Use Case.
 * Converts output data to a format suitable for the ViewModel.
 */
public class BookmarkPresenter implements BookmarkOutputBoundary {

    private final BookmarkViewModel bookmarkViewModel;

    /**
     * Constructs a BookmarkPresenter with the given ViewModel.
     *
     * @param bookmarkViewModel The ViewModel to update based on the output data.
     */
    public BookmarkPresenter(BookmarkViewModel bookmarkViewModel) {
        this.bookmarkViewModel = bookmarkViewModel;
    }

    @Override
    public void prepareSuccessView(BookmarkOutputData outputData) {
    }

    @Override
    public void prepareFailView(String error) {
        // note: this use case currently can't fail
    }
}
