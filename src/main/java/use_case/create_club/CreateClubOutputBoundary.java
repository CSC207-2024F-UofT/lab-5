package use_case.create_club;

/**
 * Output Boundary for the CreateClub usecase.
 */
public interface CreateClubOutputBoundary {

    /**
     * Runs the success view after creating a bookclub.
     * @param outputData the output data.
     */
    void prepareSuccessView(CreateClubOutputData outputData);

    /**
     * Prepares the fail view for the Join Club Use Case.
     * @param errorMessage displayed when testcase fails.
     */
    void prepareFailView(String errorMessage);

    /**
     * Switches to LoggedIn View.
     */
    void switchToLoggedInView();
}
