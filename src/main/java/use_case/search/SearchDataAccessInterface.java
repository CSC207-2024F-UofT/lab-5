package use_case.search;

import java.util.List;

import data_access.BookDataTransferObject;

/**
 * DAI for Search Use Case.
 */
public interface SearchDataAccessInterface {

    /**
     * Searches book by title.
     * @param title of the book
     * @return bookDataTransfer object
     */
    List<BookDataTransferObject> searchBookByTitle(String title);

}
