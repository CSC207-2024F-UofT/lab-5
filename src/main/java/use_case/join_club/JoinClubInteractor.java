package use_case.join_club;

/**
 * The Join Club Interactor.
 */
public class JoinClubInteractor implements JoinClubInputBoundary {

    private final JoinClubOutputBoundary joinclubOutputBoundary;
    private final JoinClubDataAccessInterface clubDataAccessInterface;

    public JoinClubInteractor(JoinClubOutputBoundary joinClubOutputBoundary,
                              JoinClubDataAccessInterface clubDataAccessInterface1) {

        this.joinclubOutputBoundary = joinClubOutputBoundary;
        this.clubDataAccessInterface = clubDataAccessInterface1;
    }

    @Override
    public void execute(JoinClubInputData joinClubInputData) {
        final String username = joinClubInputData.getUsername();
        final String clubName = joinClubInputData.getClubName();

        if (clubDataAccessInterface.isMember(username, clubName)) {
            joinclubOutputBoundary.prepareFailView("User " + username + " is already a member of the "
                    + clubName + " Book Club");
        }
        else {
            clubDataAccessInterface.addUser(username, clubName);
            final JoinClubOutputData outputData = new JoinClubOutputData(username, clubName, false);
            joinclubOutputBoundary.prepareSuccessView(outputData);
        }

    }

    @Override
    public void switchToLoggedInView() {
        joinclubOutputBoundary.switchToLoggedInView();
    }
}
