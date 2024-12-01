package data_access;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import entity.Translate;
import entity.User;
import use_case.change_password.ChangePasswordUserDataAccessInterface;
import use_case.login.LoginUserDataAccessInterface;
import use_case.logout.LogoutUserDataAccessInterface;
import use_case.signup.SignupUserDataAccessInterface;
import use_case.translator.TranslatorUserDataAccessInterface;

/**
 * In-memory implementation of the DAO for storing user data. This implementation does
 * NOT persist data between runs of the program.
 */
public class InMemoryUserDataAccessObject implements SignupUserDataAccessInterface,
        LoginUserDataAccessInterface,
        ChangePasswordUserDataAccessInterface,
        LogoutUserDataAccessInterface,
        TranslatorUserDataAccessInterface {

    private final Map<String, User> users = new HashMap<>();

    private String currentUsername;

    private final Map<String, Translate> translates = new HashMap<>();

    private final Map<User, List<Translate>> userTranslates = new HashMap<>();

    @Override
    public boolean existsByName(String identifier) {
        return users.containsKey(identifier);
    }

    @Override
    public void save(User user) {
        users.put(user.getName(), user);
    }

    @Override
    public User get(String username) {
        return users.get(username);
    }

    @Override
    public void changePassword(User user) {
        // Replace the old entry with the new password
        users.put(user.getName(), user);
    }

    @Override
    public void setCurrentUsername(String name) {
        this.currentUsername = name;
    }

    @Override
    public String getCurrentUsername() {
        return this.currentUsername;
    }

    @Override
    public void saveTranslation(Translate translation) {
        translates.put(translation.getInputText(), translation);
    }

    @Override
    public Translate getTranslation(String text) {
        return translates.get(text);
    }

    @Override
    public void deleteTranslation(String text) {
        translates.remove(text);
    }

    @Override
    public List<Translate> getAllTranslationsByUser(User user) {
        return userTranslates.get(user);
    }

    @Override
    public Translate getMostRecentTranslation(User user) {
        return userTranslates.get(user).get(0);
    }

    @Override
    public boolean existsByText(String text) {
        return users.containsKey(text);
    }

    @Override
    public void updateTranslation(User user, Translate updatedTranslation) {
        translates.put(updatedTranslation.getInputText(), updatedTranslation);

    }

    public class InMemoryUserDataAccessObject implements ChangePasswordUserDataAccessInterface {
        private User currentUser;

        @Override
        public boolean updatePassword(String newPassword) {
            if (currentUser != null) {
                currentUser.setPassword(newPassword);
                return true;
            }
            return false;
        }
    }

}
