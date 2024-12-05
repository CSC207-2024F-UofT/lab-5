package use_case.my_clubs;

import java.util.Map;

import data_access.CurrentClubManager;

/**
 * The interface of the DAO for my clubs usecase.
 */
public interface MyClubsDataAccessInterface extends CurrentClubManager {
    /**
     * Returns a map of book club names and their corresponding description.
     * @param currentUsername the username of the current user
     * @return a map of book club names and corresponding description
     */
    Map<String, String> getMyClubs(String currentUsername);
}
