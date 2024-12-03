package use_case.survey1;

import java.util.Map;

import entity.User;

/**
 * The interface of the DAO for the Survey1 Use Case.
 */
public interface Survey1UserDataAccessInterface {

    /**
     * Returns a User with this username.
     * @param username username of the user
     * @return User with this username
     */
    User get(String username);

    /**
     * Saves the preferred genres of a user.
     * @param user user
     * @param preferredGenres preferred genres
     * @return success
     */
    boolean savePreferredGenres(User user, Map<String, Integer> preferredGenres);

}
