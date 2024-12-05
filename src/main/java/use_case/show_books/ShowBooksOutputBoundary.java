package use_case.show_books;

/**
 * Output Boundary for actions that are related to show books.
 */
public interface ShowBooksOutputBoundary {

    /**
     * Prepares the success view for the Join Club Use Case.
     * @param outputData the output data
     */
    void prepareSuccessView(ShowBooksOutputData outputData);

}
