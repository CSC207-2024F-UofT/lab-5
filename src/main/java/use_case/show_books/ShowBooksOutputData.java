package use_case.show_books;

import java.util.List;

/**
 * Output data for my clubs usecase.
 */
public class ShowBooksOutputData {

    private final List<String> titles;

    public ShowBooksOutputData(List<String> titles) {
        this.titles = titles;
    }

    public List<String> getTitles() {
        return titles;
    }
}
