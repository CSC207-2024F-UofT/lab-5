package interface_adapter.bookmark;

public class BookmarkPresenter implements BookmarkOutputBoundary {
    private final BookmarkViewModel bookmarkViewModel;

    public BookmarkPresenter(BookmarkViewModel bookmarkViewModel) {
        this.bookmarkViewModel = bookmarkViewModel;
    }

    @Override
    public void prepareSuccessView(BookmarkOutputData outputData) {
        bookmarkViewModel.firePrepertyChanged("property name...");
    }
}
