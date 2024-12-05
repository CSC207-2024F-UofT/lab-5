package interface_adapter.exit_bookclub;

import use_case.exit_bookclub.ExitClubInputBoundary;
import use_case.exit_bookclub.ExitClubInputData;

/**
 * Controller for the Exit Club Use case.
 */

public class ExitClubController {

    private ExitClubInputBoundary exitClubInputBoundary;

    public ExitClubController(ExitClubInputBoundary exitClubInputBoundary) {
        this.exitClubInputBoundary = exitClubInputBoundary;

    }

    /**
     * Executes the Join Club Use Case.
     * @param userName of the user who wants to leave the club.
     * @param clubName of the club the user who wants to leave the club.
     */

    public void execute(String userName, String clubName) {
        final ExitClubInputData inputData = new ExitClubInputData(userName, clubName);
        exitClubInputBoundary.execute(inputData);
    }

}
