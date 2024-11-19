package use_case.home;

import entity.User;

/**
 * The interface of the DAO for the Change Password Use Case.
 */
public interface HomeUserDataAccessInterface {

    /**
     * Updates the system to record this user's password.
     * @param user the user whose password is to be updated
     */
//    void changePassword(User user);

    /**
     * Returns a User with this username.
     * @param username username of the user
     * @return User with this username
     */
    User get(String username);
}
