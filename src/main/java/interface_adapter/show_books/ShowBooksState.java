package interface_adapter.show_books;

import java.util.ArrayList;
import java.util.List;

/**
 * The state for showbooks state.
 */

public class ShowBooksState {

    private List<String> booksList = new ArrayList<>();

    /**
     * Gets a copy a booklist.
     *
     * @return a copy of the book club map
     */
    public List<String> getBookList() {
        return new ArrayList<>(booksList);
    }

    /**
     * Sets the list of the bookclub.
     *
     * @param booksTitle list of bookclubs.
     */
    public void setBookClubList(final List<String> booksTitle) {
        this.booksList.addAll(booksTitle);
    }
}
