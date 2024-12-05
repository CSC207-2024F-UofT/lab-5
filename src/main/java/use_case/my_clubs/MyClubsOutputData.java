package use_case.my_clubs;

import java.util.Map;

/**
 * Output data for my clubs usecase.
 */
public class MyClubsOutputData {
    // map of club name to description
    private final Map<String, String> myClubs;

    public MyClubsOutputData(Map<String, String> myClubsList) {
        this.myClubs = myClubsList;
    }

    public Map<String, String> getMyClubs() {
        return myClubs;
    }
}
