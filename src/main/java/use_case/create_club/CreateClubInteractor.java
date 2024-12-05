package use_case.create_club;

/**
 * CreateClub Interactor.
 */
public class CreateClubInteractor implements CreateClubInputBoundary {

    private final CreateClubOutputBoundary createclubOutputBoundary;
    private final CreateClubDataAccessInterface createclubDataAccessInterface;

    public CreateClubInteractor(CreateClubOutputBoundary createclubOutputBoundary,
                                CreateClubDataAccessInterface createclubDataAccessInterface) {
        this.createclubOutputBoundary = createclubOutputBoundary;
        this.createclubDataAccessInterface = createclubDataAccessInterface;
    }

    @Override
    public void execute(CreateClubInputData createClubInputData) {
        final String username = createClubInputData.getUsername();
        final String clubName = createClubInputData.getClubName();
        final String clubDescription = createClubInputData.getClubDescription();
        if (createclubDataAccessInterface.clubExists(clubName)) {
            createclubOutputBoundary.prepareFailView(clubName + " already" + " exists.");
        }
        else {
            final CreateClubOutputData outputData = new CreateClubOutputData(username, clubName, clubDescription,
                    false);
            createclubDataAccessInterface.addClub(clubName, clubDescription);
            createclubDataAccessInterface.addUser(username, clubName);
            createclubOutputBoundary.prepareSuccessView(outputData);
        }
    }

    @Override
    public void switchToLoggedInView() {
        createclubOutputBoundary.switchToLoggedInView();
    }
}

