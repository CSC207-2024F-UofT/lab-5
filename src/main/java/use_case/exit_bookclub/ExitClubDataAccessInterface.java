package use_case.exit_bookclub;

/**
 * DAO for Exit Club Use Case.
 */

public interface ExitClubDataAccessInterface {

    /**
     * Removes user from club.
     * @param userName that leaves the club
     * @param clubName name of club user is removed from.
     */

    void removeUser(String userName, String clubName);
}
