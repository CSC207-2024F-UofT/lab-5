package use_case.show_books;

import java.util.List;

/**
 * The show books club Interactor.
 */

public class ShowBooksInteractor implements ShowBooksInputBoundary {
    private final ShowBooksOutputBoundary showBooksOutputBoundary;

    private final ShowBooksDataAccessInterface showBooksDataAccessInterface;

    public ShowBooksInteractor(ShowBooksOutputBoundary showBooksOutputBoundary,
                               ShowBooksDataAccessInterface showBooksDataAccessInterface) {
        this.showBooksOutputBoundary = showBooksOutputBoundary;
        this.showBooksDataAccessInterface = showBooksDataAccessInterface;
    }

    @Override
    public void execute(ShowBooksInputData showBooksInputData) {
        final String clubName = showBooksInputData.getClubName();
        final List<String> titles = showBooksDataAccessInterface.getBooks(clubName);
        final ShowBooksOutputData outputData = new ShowBooksOutputData(titles);
        showBooksOutputBoundary.prepareSuccessView(outputData);

    }
}
