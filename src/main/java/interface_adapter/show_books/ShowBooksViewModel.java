package interface_adapter.show_books;

import interface_adapter.ViewModel;

/**
 * The state for showbooksviewmodel.
 */

public class ShowBooksViewModel extends ViewModel<ShowBooksState> {

    public ShowBooksViewModel() {
        super("Books");
        setState(new ShowBooksState());
    }
}
