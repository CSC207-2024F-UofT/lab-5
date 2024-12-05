package interface_adapter.join_club;

import use_case.join_club.JoinClubInputBoundary;
import use_case.join_club.JoinClubInputData;

/**
 * Controller for the Join Club Use Case.
 */
public class JoinClubController {
    private final JoinClubInputBoundary joinClubUseCaseInteractor;

    public JoinClubController(JoinClubInputBoundary joinClubUseCaseInteractor) {
        this.joinClubUseCaseInteractor = joinClubUseCaseInteractor;
    }

    /**
     * Executes the Join Club Use Case.
     * @param username username of the user that wants to join
     * @param clubName name of the club the user wants to join
     */
    public void execute(String username, String clubName) {
        final JoinClubInputData inputData = new JoinClubInputData(username, clubName);
        joinClubUseCaseInteractor.execute(inputData);

    }
    /**
     * Executes the Join Club Use Case.
     */

    public void switchToLoggedInView() {
        joinClubUseCaseInteractor.switchToLoggedInView();
    }

}
