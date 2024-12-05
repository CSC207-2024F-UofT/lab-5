package interface_adapter.show_books;

import use_case.show_books.ShowBooksInputBoundary;
import use_case.show_books.ShowBooksInputData;

/**
 * Controller for the Show bookclub.
 */

public class ShowBooksController {

    private final ShowBooksInputBoundary showBooksInputBoundary;

    public ShowBooksController(ShowBooksInputBoundary showBooksInputBoundary) {
        this.showBooksInputBoundary = showBooksInputBoundary;
    }

    /**
     * Excutes the cotroller.
     * @param clubName for the excute.
     */

    public void execute(String clubName) {
        final ShowBooksInputData showBooksInputData = new ShowBooksInputData(clubName);
        showBooksInputBoundary.execute(showBooksInputData);

    }
}
