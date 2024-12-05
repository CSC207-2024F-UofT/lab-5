package use_case.show_books;

/**
 * Input Boundary for actions that are related to showing a booksview.
 */

public interface ShowBooksInputBoundary {

    /**
     * Executes the join_club usecase (adds user to a club).
     * @param showBooksInputData the input data
     */
    void execute(ShowBooksInputData showBooksInputData);
}
