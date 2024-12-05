package use_case.my_clubs;

import java.util.Map;

/**
 * The my clubs usecase interactor.
 */
public class MyClubsInteractor implements MyClubsInputBoundary {
    private final MyClubsDataAccessInterface myClubsDataAccessInterface;
    private final MyClubsOutputBoundary myClubsOutputBoundary;

    public MyClubsInteractor(MyClubsDataAccessInterface myClubsDataAccessInterface,
                             MyClubsOutputBoundary myClubsOutputBoundary) {
        this.myClubsDataAccessInterface = myClubsDataAccessInterface;
        this.myClubsOutputBoundary = myClubsOutputBoundary;
    }

    @Override
    public void execute(MyClubsInputData myClubsInputData) {
        final String currentUsername = myClubsInputData.getCurrentUsername();
        final Map<String, String> myClubs = myClubsDataAccessInterface.getMyClubs(currentUsername);

        if (myClubs.isEmpty()) {
            myClubsOutputBoundary.prepareFailView();
        }
        else {
            final MyClubsOutputData myClubsOutputData = new MyClubsOutputData(myClubs);
            myClubsOutputBoundary.prepareSuccessView(myClubsOutputData);
        }
    }

    @Override
    public void switchToShowNotesView(String currentClub) {
        myClubsDataAccessInterface.setCurrentClub(currentClub);
        myClubsOutputBoundary.switchToShowNotesView(currentClub);
    }
}
