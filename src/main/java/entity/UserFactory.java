package entity;

/**
 * Factory for creating users.
 */
public interface UserFactory {
    /**
     * Creates a new User.
     * @param accessToken the name of the new user
     // * @param password the password of the new user
     * @return the new user
     */
    User create(String accessToken);

}
