package interface_adapter.create_club;

import use_case.create_club.CreateClubInputBoundary;
import use_case.create_club.CreateClubInputData;

/**
 * Controller for the Create Club Use Case.
 */
public class CreateClubController {
    private final CreateClubInputBoundary createClubUseCaseInteractor;

    public CreateClubController(CreateClubInputBoundary createClubUseCaseInteractor) {
        this.createClubUseCaseInteractor = createClubUseCaseInteractor;
    }

    /**
     * Executes the Create Club Use Case.
     *
     * @param clubDescription description of the club
     * @param clubName        name of the club the user wants to join
     * @param username        the username of the person creating the bookclub.
     */
    public void execute(String clubName, String clubDescription, String username) {
        final CreateClubInputData inputData = new CreateClubInputData(clubName, clubDescription, username);
        createClubUseCaseInteractor.execute(inputData);
    }

    /**
     * Switches to LoggedInView.
     */
    public void switchToLoggedInView() {
        createClubUseCaseInteractor.switchToLoggedInView();
    }

}
