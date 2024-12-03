package use_case.survey_second_page;

import entity.User;

/**
 * DAO for the Survey Use Case.
 */
public interface SurveySecondPageDataAccessInterface {

    /**
     * Checks if the given movie exists.
     * @param movie the movie to look for
     * @return true if the movie with the given name exists; false otherwise
     */
    boolean movieExists(String movie);

    /**
     * Saves the user.
     * @param movie the movie to save
     */
    void save(User movie);

    /**
     * Returns the movie with the given title.
     * @param movie the movie to look up
     * @return the movie with the given title
     */
    User get(String movie);
}
