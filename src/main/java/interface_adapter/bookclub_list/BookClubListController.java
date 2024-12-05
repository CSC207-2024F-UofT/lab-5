package interface_adapter.bookclub_list;

import use_case.bookclub_list.BookClubInputBoundary;

/**
 * Represents a bookclublist controller.
 *
 * @null This object may be null if no book club data is available.
 */

public class BookClubListController {

    private final BookClubInputBoundary bookClubUseCaseInteractor;

    public BookClubListController(BookClubInputBoundary bookClubUseCaseInteractor) {
        this.bookClubUseCaseInteractor = bookClubUseCaseInteractor;
    }

    /**
     * Excutes the cotroller.
     *
     */

    public void execute() {
        bookClubUseCaseInteractor.execute();
    }

}
