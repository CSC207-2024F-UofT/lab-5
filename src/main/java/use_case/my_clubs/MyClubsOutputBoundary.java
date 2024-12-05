package use_case.my_clubs;

/**
 * The output boundary for my clubs usecase.
 */
public interface MyClubsOutputBoundary {
    /**
     * Prepares the success view for my clubs usecase.
     * @param myClubsOutputData the output data
     */
    void prepareSuccessView(MyClubsOutputData myClubsOutputData);

    /**
     * Prepares the fail view for my clubs usecase.
     */
    void prepareFailView();

    /**
     * Switches to the show Notes view.
     * @param currentClub the club the user selected
     */
    void switchToShowNotesView(String currentClub);
}
