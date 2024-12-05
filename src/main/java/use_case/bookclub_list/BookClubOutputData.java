package use_case.bookclub_list;

import java.util.List;

/**
 * Data transfer object for output related to book club information.
 * Contains the list of book clubs to be passed to the output boundary.
 */
public final class BookClubOutputData {

    private List<String> bookClubListName;

    public BookClubOutputData(List<String> bookClubListName) {
        this.bookClubListName = bookClubListName;
    }

    public List<String> getBookClubListName() {
        return bookClubListName;
    }
}
