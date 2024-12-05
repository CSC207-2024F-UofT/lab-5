package use_case.bookclub_list;

import java.util.ArrayList;
import java.util.List;

import entity.BookClub;

/**
 * Interactor class for handling the book club-related use cases.
 * Implements the BookClubInputBoundary interface to execute book club operations.
 */
public final class BookClubInteractor implements BookClubInputBoundary {

    private final BookClubOutputBoundary bookClubOutputBoundary;
    private final BookClubDataAccessInterface bookClubDataAccessInterface;

    /**
     * Constructs a new BookClubInteractor with the required dependencies.
     *
     * @param bookClubOutputBoundary the output boundary to prepare the success view
     * @param bookClubDataAccessInterface the data access interface for retrieving book clubs
     */
    public BookClubInteractor(BookClubOutputBoundary bookClubOutputBoundary,
                              BookClubDataAccessInterface bookClubDataAccessInterface) {
        this.bookClubOutputBoundary = bookClubOutputBoundary;
        this.bookClubDataAccessInterface = bookClubDataAccessInterface;
    }

    /**
     * Executes the book club operation to retrieve all book clubs and prepare the success view.
     */
    @Override
    public void execute() {
        final List<BookClub> bookClubs = bookClubDataAccessInterface.getAllClubs();
        final List<String> bookClubListName = new ArrayList<>();
        for (final BookClub bookClub : bookClubs) {
            final String display = bookClub.getName() + " - " + bookClub.getDescription();
            bookClubListName.add(display);
        }
        final BookClubOutputData bookClubOutputData = new BookClubOutputData(bookClubListName);
        bookClubOutputBoundary.prepareSuccessView(bookClubOutputData);
    }
}
