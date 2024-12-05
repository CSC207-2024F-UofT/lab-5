package use_case.my_clubs;

/**
 * Input data for my clubs usecase.
 */
public class MyClubsInputData {
    private final String currentUsername;

    public MyClubsInputData(String currentUsername) {
        this.currentUsername = currentUsername;
    }

    public String getCurrentUsername() {
        return currentUsername;
    }
}
