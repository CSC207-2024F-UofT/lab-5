package use_case.my_clubs;

/**
 * The input boundary for my clubs usecase.
 */
public interface MyClubsInputBoundary {
    /**
     * Executes my clubs usecase.
     * @param myClubsInputData the input data
     */
    void execute(MyClubsInputData myClubsInputData);

    /**
     * Switches to the show notes view of the current club.
     * @param currentClub the club the user selected
     */
    void switchToShowNotesView(String currentClub);
}
