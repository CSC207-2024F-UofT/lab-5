package use_case.profile;

import entity.User;

/**
 * Access the user object of the currently logged-in User.
 */
public interface ProfileDataAccessInterface {

    /**
     * Access the user object from Data access with username.
     * @param username the name of the user.
     * @return User object associated with the username.
     */
    User get(String username);
}
