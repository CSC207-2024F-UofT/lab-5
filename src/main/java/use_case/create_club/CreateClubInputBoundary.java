package use_case.create_club;

/**
 * InputBoundary for the CreateClub usecase.
 */
public interface CreateClubInputBoundary {

    /**
     * Executes the usecase and creates a new club.
     * @param inputData is the input data.
     */
    void execute(CreateClubInputData inputData);

    /**
     * Switches to LoggedInView.
     */
    void switchToLoggedInView();
}
