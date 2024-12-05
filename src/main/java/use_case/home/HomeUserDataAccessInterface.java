package use_case.home;

import entity.User;

/**
 * The interface of the DAO for the Change Password Use Case.
 */
public interface HomeUserDataAccessInterface {

    /**
     * Returns a User with this username.
     * @param username username of the user
     * @return User with this username
     */
    User get(String username);
}
