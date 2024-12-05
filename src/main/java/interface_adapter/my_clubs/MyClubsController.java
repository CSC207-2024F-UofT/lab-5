package interface_adapter.my_clubs;

import use_case.my_clubs.MyClubsInputBoundary;
import use_case.my_clubs.MyClubsInputData;

/**
 * Controller for my clubs usecase.
 */
public class MyClubsController {
    private final MyClubsInputBoundary myClubsInteractor;

    public MyClubsController(MyClubsInputBoundary myClubsInteractor) {
        this.myClubsInteractor = myClubsInteractor;
    }

    /**
     * Executes my clubs usecase.
     * @param currentUsername the username of the current user
     */
    public void execute(String currentUsername) {
        final MyClubsInputData myClubsInputData = new MyClubsInputData(currentUsername);
        myClubsInteractor.execute(myClubsInputData);
    }

    /**
     * Switches to show message view for the club the user selected.
     * @param currentClub the club the user selected
     */
    public void switchToShowMessageView(String currentClub) {
        myClubsInteractor.switchToShowNotesView(currentClub);
    }
}
