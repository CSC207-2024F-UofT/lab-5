package interface_adapter.bookmark;

import interface_adapter.ViewModel;

/**
 * The ViewModel for the Bookmark Use Case.
 * Notifies the view of changes to the bookmark state.
 */
public class BookmarkViewModel extends ViewModel<BookmarkState> {

    public static final String TITLE_LABEL = "Bookmark Manager";
    public static final String ADD_BOOKMARK_BUTTON_LABEL = "Add Bookmark";
    public static final String REMOVE_BOOKMARK_BUTTON_LABEL = "Remove Bookmark";
    public static final String BACK_BUTTON_LABEL = "Back";

    public BookmarkViewModel() {
        super("bookmark");
        setState(new BookmarkState());
    }
}
