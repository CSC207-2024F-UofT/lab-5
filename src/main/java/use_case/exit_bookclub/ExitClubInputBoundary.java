package use_case.exit_bookclub;

/**
 * Input Boundary for actions that are related to exit a BookClub.
 */

public interface ExitClubInputBoundary {
    /**
     * Executes the exitclub usecase (removes user for the club).
     * @param exitClubInputData the input data
     */

    void execute(ExitClubInputData exitClubInputData);

}
