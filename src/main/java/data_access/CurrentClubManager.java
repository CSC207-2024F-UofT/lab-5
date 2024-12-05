package data_access;

/**
 * Manager for the current club.
 */
public interface CurrentClubManager {
    /**
     * Sets the current club of the user has decided to view.
     * @param currentClub the username of the user that is logged in
     */
    void setCurrentClub(String currentClub);

    /**
     * Gets the current club the user has decided to view.
     * @return current club.
     */
    String getCurrentClub();

}
