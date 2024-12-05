package interface_adapter.my_clubs;

import java.util.HashMap;
import java.util.Map;

/**
 * The state for my clubs view model.
 */
public class MyClubsState {
    private String currentUsername;
    private String currentClub;
    private Map<String, String> myClubs = new HashMap<>();

    public String getCurrentUsername() {
        return currentUsername;
    }

    public void setCurrentUsername(String currentUsername) {
        this.currentUsername = currentUsername;
    }

    public Map<String, String> getMyClubs() {
        return myClubs;
    }

    public void setMyClubs(Map<String, String> myClubs) {
        this.myClubs = myClubs;
    }

    public String getCurrentClub() {
        return currentClub;
    }

    public void setCurrentClub(String currentClub) {
        this.currentClub = currentClub;
    }
}
