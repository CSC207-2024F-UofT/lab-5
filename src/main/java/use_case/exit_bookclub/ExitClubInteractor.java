package use_case.exit_bookclub;

/**
 * Interactor class for handling the book club-related use cases.
 * Implements the BookClubInputBoundary interface to execute book club operations.
 */

public class ExitClubInteractor implements ExitClubInputBoundary {

    private final ExitClubOutputBoundary exitClubOutputBoundary;
    private final ExitClubDataAccessInterface exitClubDataAccessInterface;

    public ExitClubInteractor(ExitClubOutputBoundary exitClubOutputBoundary,
                              ExitClubDataAccessInterface exitClubDataAccessInterface) {
        this.exitClubOutputBoundary = exitClubOutputBoundary;
        this.exitClubDataAccessInterface = exitClubDataAccessInterface;
    }

    @Override
    public void execute(ExitClubInputData exitClubInputData) {
        final String userName = exitClubInputData.getUsername();
        final String clubName = exitClubInputData.getClubName();
        exitClubDataAccessInterface.removeUser(userName, clubName);
        exitClubOutputBoundary.prepareSuccessView();

    }
}
