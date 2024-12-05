package use_case.join_club;

/**
 * Input Boundary for actions that are related to joining a BookClub.
 */

public interface JoinClubInputBoundary {
    /**
     * Executes the join_club usecase (adds user to a club).
     * @param joinClubInputData the input data
     */
    void execute(JoinClubInputData joinClubInputData);

    /**
     * Executes the join_club usecase (adds user to a club).
     *
     */
    void switchToLoggedInView();
}
